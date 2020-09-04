package cn.dhbin.minion.core.swagger.plugin.javac;

import cn.dhbin.minion.core.swagger.plugin.Constant;
import cn.dhbin.minion.core.swagger.plugin.adapter.impl.ComposeAdapterImpl;
import cn.dhbin.minion.core.swagger.plugin.metadata.doc.DocInfo;
import cn.dhbin.minion.core.swagger.plugin.metadata.doc.ParamDoc;
import cn.dhbin.minion.core.swagger.plugin.metadata.swagger.ApiImplicitParamMetadata;
import cn.dhbin.minion.core.swagger.plugin.metadata.swagger.ApiOperationMetadata;
import cn.dhbin.minion.core.swagger.plugin.metadata.swagger.ApiParamMetadata;
import cn.dhbin.minion.core.swagger.plugin.spi.DocumentParser;
import cn.dhbin.minion.core.swagger.plugin.util.Services;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.sun.source.doctree.DocCommentTree;
import com.sun.source.tree.Tree;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.List;
import org.springframework.web.multipart.MultipartFile;

import javax.lang.model.element.Element;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author donghaibin
 * @date 2020/8/6
 */
public class MappingTreeTranslator extends AbstractConditionTreeTranslator {

    private final ComposeAdapterImpl docInfoAdapter = new ComposeAdapterImpl();

    private final DocumentParser documentParser = Services.getDocumentParser();

    private final List<String> mappingClassStr = List.of(
            Constant.SPRING_REQUEST_MAPPING,
            Constant.SPRING_GET_MAPPING,
            Constant.SPRING_POST_MAPPING,
            Constant.SPRING_PATCH_MAPPING,
            Constant.SPRING_PUT_MAPPING,
            Constant.SPRING_DELETE_MAPPING
    );

    private static final Map<String, String> TYPE_NAME_LOOKUP = new HashMap<>();

    private static final List<String> BASIC_TYPE = List.of(
            Long.TYPE.getName(),
            Short.TYPE.getName(),
            Integer.TYPE.getName(),
            Double.TYPE.getName(),
            Float.TYPE.getName(),
            Byte.TYPE.getName(),
            Boolean.TYPE.getName()
    );

    public MappingTreeTranslator(JavacProcessingEnvironment environment, Element element) {
        super(environment, element);
        TYPE_NAME_LOOKUP.put(Long.TYPE.getName(), "long");
        TYPE_NAME_LOOKUP.put(Short.TYPE.getName(), "int");
        TYPE_NAME_LOOKUP.put(Integer.TYPE.getName(), "int");
        TYPE_NAME_LOOKUP.put(Double.TYPE.getName(), "double");
        TYPE_NAME_LOOKUP.put(Float.TYPE.getName(), "float");
        TYPE_NAME_LOOKUP.put(Byte.TYPE.getName(), "byte");
        TYPE_NAME_LOOKUP.put(Boolean.TYPE.getName(), "boolean");
        TYPE_NAME_LOOKUP.put(Character.TYPE.getName(), "string");
        TYPE_NAME_LOOKUP.put(Date.class.getName(), "date-time");
        TYPE_NAME_LOOKUP.put(java.sql.Date.class.getName(), "date");
        TYPE_NAME_LOOKUP.put(String.class.getName(), "string");
        TYPE_NAME_LOOKUP.put(Object.class.getName(), "object");
        TYPE_NAME_LOOKUP.put(Long.class.getName(), "long");
        TYPE_NAME_LOOKUP.put(Integer.class.getName(), "int");
        TYPE_NAME_LOOKUP.put(Short.class.getName(), "int");
        TYPE_NAME_LOOKUP.put(Double.class.getName(), "double");
        TYPE_NAME_LOOKUP.put(Float.class.getName(), "float");
        TYPE_NAME_LOOKUP.put(Boolean.class.getName(), "boolean");
        TYPE_NAME_LOOKUP.put(Byte.class.getName(), "byte");
        TYPE_NAME_LOOKUP.put(BigDecimal.class.getName(), "bigdecimal");
        TYPE_NAME_LOOKUP.put(BigInteger.class.getName(), "biginteger");
        TYPE_NAME_LOOKUP.put(Currency.class.getName(), "string");
        TYPE_NAME_LOOKUP.put(UUID.class.getName(), "uuid");
        TYPE_NAME_LOOKUP.put(MultipartFile.class.getName(), "__file");
    }

    @Override
    protected <T extends JCTree> T process(T tree) {
        JCTree.JCMethodDecl methodDecl = (JCTree.JCMethodDecl) tree;
        DocCommentTree docCommentTree = this.getDocCommentTree(methodDecl.sym);
        if (docCommentTree == null) {
            return tree;
        }
        DocInfo docInfo = docInfoAdapter.convert(docCommentTree);
        docInfoAdapter.obtainName(tree, docInfo);
        docInfoAdapter.paramDocProcess(methodDecl, docInfo);

        if (!existApiOperationAnnotation(methodDecl)) {
            addApiOperation(methodDecl, docInfo);
        }
        // 添加ApiParam的优先级比ApiImplicitParam高，添加ApiParam后不添加ApiImplicitParam
        if (!addApiParam(methodDecl, docInfo)) {
            if (!existApiImplicitParamsAnnotation(methodDecl)) {
                addApiImplicitParams(methodDecl, docInfo);
            }
        }
        return tree;
    }

    private boolean addApiParam(JCTree.JCMethodDecl methodDecl, DocInfo docInfo) {
        java.util.List<ApiParamMetadata> apiParamMetadataList = documentParser.resolveApiParam(docInfo);
        if (CollUtil.isEmpty(apiParamMetadataList)) {
            return false;
        }
        for (JCTree.JCVariableDecl variableDecl : methodDecl.params) {
            for (ApiParamMetadata apiParamMetadata : apiParamMetadataList) {
                if (apiParamMetadata.getName().equals(variableDecl.name.toString())) {
                    JCTree.JCAnnotation jcAnnotation = createApiParam(apiParamMetadata);
                    variableDecl.mods.annotations = variableDecl.mods.annotations.prepend(jcAnnotation);
                }
            }
        }
        return true;
    }

    private boolean existApiOperationAnnotation(JCTree.JCMethodDecl tree) {
        com.sun.tools.javac.util.List<JCTree.JCAnnotation> annotations = tree.getModifiers().getAnnotations();
        for (JCTree.JCAnnotation annotation : annotations) {
            if (annotation.type != null && annotation.type.toString().equals(Constant.SWAGGER_API_OPERATION_CLASS_NAME)) {
                return true;
            }
        }
        return false;
    }

    private boolean existApiImplicitParamsAnnotation(JCTree.JCMethodDecl tree) {
        com.sun.tools.javac.util.List<JCTree.JCAnnotation> annotations = tree.getModifiers().getAnnotations();
        for (JCTree.JCAnnotation annotation : annotations) {
            if (annotation.type != null && annotation.type.toString().equals(Constant.SWAGGER_API_IMPLICIT_PARAMS_CLASS_NAME)) {
                return true;
            }
        }
        return false;
    }

    private void addApiImplicitParams(JCTree.JCMethodDecl methodDecl, DocInfo docInfo) {
        java.util.List<ApiImplicitParamMetadata> apiImplicitParamMetadata = documentParser.resolveApiImplicitParam(docInfo);
        if (apiImplicitParamMetadata != null) {
            java.util.List<JCTree.JCAnnotation> apiImplicitParamAnnotationList = apiImplicitParamMetadata.stream()
                    .map(this::createApiImplicitParam)
                    .collect(Collectors.toList());
            if (!apiImplicitParamAnnotationList.isEmpty()) {
                JCTree.JCAnnotation apiImplicitParams = createApiImplicitParams(List.from(apiImplicitParamAnnotationList));
                methodDecl.mods.annotations = methodDecl.getModifiers().getAnnotations().prepend(apiImplicitParams);
            }
        }
    }

    private void addApiOperation(JCTree.JCMethodDecl methodDecl, DocInfo docInfo) {
        ApiOperationMetadata apiOperationMetadata = documentParser.resolveApiOperation(docInfo);
        if (apiOperationMetadata != null) {
            Map<String, JCTree.JCExpression> jcExpressionMap = new HashMap<>(16);
            if (ObjectUtil.isNotNull(apiOperationMetadata.getValue())) {
                jcExpressionMap.put("value", createLiteral(apiOperationMetadata.getValue()));
            }
            if (ObjectUtil.isNotNull(apiOperationMetadata.getNotes())) {
                jcExpressionMap.put("notes", createLiteral(apiOperationMetadata.getNotes()));
            }
            if (CollUtil.isNotEmpty(apiOperationMetadata.getTags())) {
                java.util.List<JCTree.JCExpression> tagsValue = apiOperationMetadata.getTags().stream()
                        .map((Function<String, JCTree.JCExpression>) this::createLiteral)
                        .collect(Collectors.toList());
                jcExpressionMap.put("tags", createNewArray(List.from(tagsValue)));
            }
            if (ObjectUtil.isNotNull(apiOperationMetadata.getNickname())) {
                jcExpressionMap.put("nickname", createLiteral(apiOperationMetadata.getNickname()));
            }
            if (ObjectUtil.isNotNull(apiOperationMetadata.getProduces())) {
                jcExpressionMap.put("produces", createLiteral(apiOperationMetadata.getProduces()));
            }
            if (ObjectUtil.isNotNull(apiOperationMetadata.getConsumes())) {
                jcExpressionMap.put("consumes", createLiteral(apiOperationMetadata.getConsumes()));
            }
            if (ObjectUtil.isNotNull(apiOperationMetadata.getProtocols())) {
                jcExpressionMap.put("protocols", createLiteral(apiOperationMetadata.getProduces()));
            }
            if (CollUtil.isNotEmpty(apiOperationMetadata.getAuthorizations())) {
                java.util.List<JCTree.JCAnnotation> annotations = apiOperationMetadata.getAuthorizations()
                        .stream()
                        .map(s -> {
                            Map<String, JCTree.JCExpression> map = new HashMap<>(16);
                            map.put("value", createLiteral(s));
                            return createAnnotation(Constant.SWAGGER_AUTHORIZATION_CLASS_NAME, map);
                        })
                        .collect(Collectors.toList());
                jcExpressionMap.put("authorizations", createNewArray(com.sun.tools.javac.util.List.from(annotations)));
            }
            if (ObjectUtil.isNotNull(apiOperationMetadata.getCode())) {
                jcExpressionMap.put("code", createLiteral(apiOperationMetadata.getCode()));
            }
            JCTree.JCAnnotation apiAnnotation = createAnnotation(Constant.SWAGGER_API_OPERATION_CLASS_NAME, jcExpressionMap);
            methodDecl.mods.annotations = methodDecl.getModifiers().getAnnotations().prepend(apiAnnotation);
        }
    }

    @Override
    public boolean test(JCTree jcTree) {
        try {
            if (jcTree == null || jcTree.getKind() == null || !jcTree.getKind().equals(Tree.Kind.METHOD)) {
                return false;
            }
        } catch (AssertionError e) {
            return false;
        }

        JCTree.JCMethodDecl methodDecl = (JCTree.JCMethodDecl) jcTree;
        List<JCTree.JCAnnotation> annotations = methodDecl.getModifiers().getAnnotations();
        for (JCTree.JCAnnotation annotation : annotations) {
            if (annotation.type != null) {
                return mappingClassStr.contains(annotation.type.toString());
            }
        }
        return false;
    }

    private JCTree.JCAnnotation createApiImplicitParams(List<JCTree.JCExpression> apiImplicitParam) {
        JCTree.JCNewArray newArray = treeMaker.NewArray(null, List.nil(), apiImplicitParam);
        JCTree.JCAssign jcAssign = createAssign("value", newArray);
        return createAnnotation(Constant.SWAGGER_API_IMPLICIT_PARAMS_CLASS_NAME, List.of(jcAssign));
    }

    private JCTree.JCAnnotation createApiImplicitParam(ApiImplicitParamMetadata metadata) {
        Map<String, JCTree.JCExpression> expressionMap = new HashMap<>(16);
        if (ObjectUtil.isNotNull(metadata.getName())) {
            expressionMap.put("name", createLiteral(metadata.getName()));
        }
        if (ObjectUtil.isNotNull(metadata.getValue())) {
            expressionMap.put("value", createLiteral(metadata.getValue()));
        }
        if (ObjectUtil.isNotNull(metadata.getDefaultValue())) {
            expressionMap.put("defaultValue", createLiteral(metadata.getDefaultValue()));
        }
        if (ObjectUtil.isNotNull(metadata.getAllowableValues())) {
            expressionMap.put("allowableValues", createLiteral(metadata.getAllowableValues()));
        }
        if (ObjectUtil.isNotNull(metadata.getRequired())) {
            expressionMap.put("required", createLiteral(metadata.getRequired()));
        }
        if (ObjectUtil.isNotNull(metadata.getAccess())) {
            expressionMap.put("access", createLiteral(metadata.getAccess()));
        }
        if (ObjectUtil.isNotNull(metadata.getAllowMultiple())) {
            expressionMap.put("allowMultiple", createLiteral(metadata.getAllowMultiple()));
        }
        if (ObjectUtil.isNotNull(metadata.getDataType())) {
            String dataType = TYPE_NAME_LOOKUP.get(metadata.getDataType());
            if (dataType != null) {
                expressionMap.put("dataType", createLiteral(dataType));
            }
            if (!BASIC_TYPE.contains(metadata.getDataType())) {
                JCTree.JCFieldAccess classFieldAccess = createClassFieldAccess(metadata.getDataType());
                expressionMap.put("dataTypeClass", classFieldAccess);
            }
        }
        if (ObjectUtil.isNotNull(metadata.getParamType())) {
            expressionMap.put("paramType", createLiteral(metadata.getParamType()));
        }
        if (ObjectUtil.isNotNull(metadata.getExample())) {
            expressionMap.put("example", createLiteral(metadata.getExample()));
        }
        if (ObjectUtil.isNotNull(metadata.getType())) {
            expressionMap.put("type", createLiteral(metadata.getType()));
        }
        if (ObjectUtil.isNotNull(metadata.getFormat())) {
            expressionMap.put("format", createLiteral(metadata.getFormat()));
        }
        if (ObjectUtil.isNotNull(metadata.getAllowEmptyValue())) {
            expressionMap.put("allowEmptyValue", createLiteral(metadata.getAllowEmptyValue()));
        }
        if (ObjectUtil.isNotNull(metadata.getReadOnly())) {
            expressionMap.put("readOnly", createLiteral(metadata.getReadOnly()));
        }
        if (ObjectUtil.isNotNull(metadata.getCollectionFormat())) {
            expressionMap.put("collectionFormat", createLiteral(metadata.getCollectionFormat()));
        }

        return createAnnotation(Constant.SWAGGER_API_IMPLICIT_PARAM_CLASS_NAME, expressionMap);
    }

    private JCTree.JCAnnotation createApiParam(ApiParamMetadata metadata) {
        Map<String, JCTree.JCExpression> expressionMap = new HashMap<>(16);
        if (ObjectUtil.isNotNull(metadata.getName())) {
            expressionMap.put("name", createLiteral(metadata.getName()));
        }
        if (ObjectUtil.isNotNull(metadata.getValue())) {
            expressionMap.put("value", createLiteral(metadata.getValue()));
        }
        if (ObjectUtil.isNotNull(metadata.getDefaultValue())) {
            expressionMap.put("defaultValue", createLiteral(metadata.getDefaultValue()));
        }
        if (ObjectUtil.isNotNull(metadata.getAllowableValues())) {
            expressionMap.put("allowableValues", createLiteral(metadata.getAllowableValues()));
        }
        if (ObjectUtil.isNotNull(metadata.getRequired())) {
            expressionMap.put("required", createLiteral(metadata.getRequired()));
        }
        if (ObjectUtil.isNotNull(metadata.getAccess())) {
            expressionMap.put("access", createLiteral(metadata.getAccess()));
        }
        if (ObjectUtil.isNotNull(metadata.getAllowableValues())) {
            expressionMap.put("allowMultiple", createLiteral(metadata.getAllowMultiple()));
        }
        if (ObjectUtil.isNotNull(metadata.getHidden())) {
            expressionMap.put("hidden", createLiteral(metadata.getHidden()));
        }
        if (ObjectUtil.isNotNull(metadata.getExample())) {
            expressionMap.put("example", createLiteral(metadata.getExample()));
        }
        if (ObjectUtil.isNotNull(metadata.getType())) {
            expressionMap.put("type", createLiteral(metadata.getType()));
        }
        if (ObjectUtil.isNotNull(metadata.getFormat())) {
            expressionMap.put("format", createLiteral(metadata.getFormat()));
        }
        if (ObjectUtil.isNotNull(metadata.getAllowEmptyValue())) {
            expressionMap.put("allowEmptyValue", createLiteral(metadata.getAllowEmptyValue()));
        }
        if (ObjectUtil.isNotNull(metadata.getReadOnly())) {
            expressionMap.put("readOnly", createLiteral(metadata.getReadOnly()));
        }
        if (ObjectUtil.isNotNull(metadata.getCollectionFormat())) {
            expressionMap.put("collectionFormat", createLiteral(metadata.getCollectionFormat()));
        }
        return createAnnotation(Constant.SWAGGER_API_PARAM_CLASS_NAME, expressionMap);
    }
}
