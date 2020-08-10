package cn.dhbin.minion.core.swagger.plugin.javac;

import cn.dhbin.minion.core.swagger.plugin.Constant;
import cn.dhbin.minion.core.swagger.plugin.adapter.impl.ComposeAdapterImpl;
import cn.dhbin.minion.core.swagger.plugin.metadata.doc.DocInfo;
import cn.dhbin.minion.core.swagger.plugin.metadata.swagger.ApiMetadata;
import cn.dhbin.minion.core.swagger.plugin.spi.DocumentParser;
import cn.dhbin.minion.core.swagger.plugin.util.Services;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.sun.source.doctree.DocCommentTree;
import com.sun.source.tree.Tree;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;

import javax.lang.model.element.Element;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author donghaibin
 * @date 2020/8/6
 */
public class ControllerTreeTranslator extends AbstractConditionTreeTranslator {

    private final ComposeAdapterImpl docInfoAdapter = new ComposeAdapterImpl();

    private final DocumentParser documentParser = Services.getDocumentParser();

    private final List<String> controllerClassNames = Arrays.asList(
            Constant.SPRING_REST_CONTROLLER_CLASS_NAME,
            Constant.SPRING_CONTROLLER_CLASS_NAME
    );

    public ControllerTreeTranslator(JavacProcessingEnvironment environment, Element element) {
        super(environment, element);
    }

    @Override
    protected <T extends JCTree> T process(T tree) {
        JCTree.JCClassDecl classDecl = (JCTree.JCClassDecl) tree;
        DocCommentTree docCommentTree = this.getDocCommentTree(classDecl.sym);
        if (docCommentTree == null) {
            return tree;
        }
        DocInfo docInfo = docInfoAdapter.convert(docCommentTree);
        docInfoAdapter.obtainName(tree, docInfo);
        ApiMetadata apiMetadata = documentParser.resolveApi(docInfo);
        if (apiMetadata == null) {
            return tree;
        }
        if (!existApiAnnotation(classDecl)) {
            Map<String, JCTree.JCExpression> jcExpressionMap = new HashMap<>(16);
            addValue(apiMetadata, jcExpressionMap);
            addTags(apiMetadata, jcExpressionMap);
            addProduces(apiMetadata, jcExpressionMap);
            addConsumes(apiMetadata, jcExpressionMap);
            addProtocols(apiMetadata, jcExpressionMap);
            addAuthorizations(apiMetadata, jcExpressionMap);
            addHidden(apiMetadata, jcExpressionMap);
            JCTree.JCAnnotation apiAnnotation = createAnnotation(Constant.SWAGGER_API_CLASS_NAME, jcExpressionMap);
            classDecl.mods.annotations = classDecl.getModifiers().getAnnotations().prepend(apiAnnotation);
        }
        return tree;
    }

    @Override
    public boolean test(JCTree jcTree) {
        try {
            if (jcTree == null || jcTree.getKind() == null || !jcTree.getKind().equals(Tree.Kind.CLASS)) {
                return false;
            }
        } catch (AssertionError e) {
            return false;
        }
        JCTree.JCClassDecl classDecl = (JCTree.JCClassDecl) jcTree;
        List<JCTree.JCAnnotation> annotations = classDecl.getModifiers().getAnnotations();
        for (JCTree.JCAnnotation annotation : annotations) {
            if (annotation.type != null && controllerClassNames.contains(annotation.type.toString())) {
                return true;
            }
        }
        return false;
    }

    private boolean existApiAnnotation(JCTree.JCClassDecl tree) {
        com.sun.tools.javac.util.List<JCTree.JCAnnotation> annotations = tree.getModifiers().getAnnotations();
        for (JCTree.JCAnnotation annotation : annotations) {
            if (annotation.type != null && annotation.type.toString().equals(Constant.SWAGGER_API_CLASS_NAME)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 为@Api的hidden变量赋值
     *
     * @param apiMetadata     apiMetadata
     * @param jcExpressionMap jcExpressionMap
     */
    protected void addHidden(ApiMetadata apiMetadata, Map<String, JCTree.JCExpression> jcExpressionMap) {
        if (apiMetadata.getHidden() != null) {
            jcExpressionMap.put("hidden", createLiteral(apiMetadata.getHidden()));
        }
    }

    /**
     * 为@Api的authorizations变量赋值
     *
     * @param apiMetadata     apiMetadata
     * @param jcExpressionMap jcExpressionMap
     */
    protected void addAuthorizations(ApiMetadata apiMetadata, Map<String, JCTree.JCExpression> jcExpressionMap) {
        if (CollUtil.isNotEmpty(apiMetadata.getAuthorizations())) {
            List<JCTree.JCAnnotation> annotations = apiMetadata.getAuthorizations()
                    .stream()
                    .map(s -> {
                        Map<String, JCTree.JCExpression> map = new HashMap<>(16);
                        map.put("value", createLiteral(s));
                        return createAnnotation(Constant.SWAGGER_AUTHORIZATION_CLASS_NAME, map);
                    })
                    .collect(Collectors.toList());
            jcExpressionMap.put("authorizations", createNewArray(com.sun.tools.javac.util.List.from(annotations)));
        }
    }

    /**
     * 为@Api的protocols变量赋值
     *
     * @param apiMetadata     apiMetadata
     * @param jcExpressionMap jcExpressionMap
     */
    protected void addProtocols(ApiMetadata apiMetadata, Map<String, JCTree.JCExpression> jcExpressionMap) {
        if (StrUtil.isNotBlank(apiMetadata.getProduces())) {
            jcExpressionMap.put("protocols", createLiteral(apiMetadata.getProduces()));
        }
    }


    /**
     * 为@Api的consumes变量赋值
     *
     * @param apiMetadata     apiMetadata
     * @param jcExpressionMap jcExpressionMap
     */
    protected void addConsumes(ApiMetadata apiMetadata, Map<String, JCTree.JCExpression> jcExpressionMap) {
        if (StrUtil.isNotBlank(apiMetadata.getConsumes())) {
            jcExpressionMap.put("consumes", createLiteral(apiMetadata.getConsumes()));
        }
    }

    /**
     * 为@Api的produces变量赋值
     *
     * @param apiMetadata     apiMetadata
     * @param jcExpressionMap jcExpressionMap
     */
    protected void addProduces(ApiMetadata apiMetadata, Map<String, JCTree.JCExpression> jcExpressionMap) {
        if (StrUtil.isNotBlank(apiMetadata.getProduces())) {
            jcExpressionMap.put("produces", createLiteral(apiMetadata.getProduces()));
        }
    }


    /**
     * 为@Api的value变量赋值
     *
     * @param apiMetadata     apiMetadata
     * @param jcExpressionMap jcExpressionMap
     */
    protected void addValue(ApiMetadata apiMetadata, Map<String, JCTree.JCExpression> jcExpressionMap) {
        if (StrUtil.isNotBlank(apiMetadata.getValue())) {
            jcExpressionMap.put("value", createLiteral(apiMetadata.getValue()));
        }
    }

    /**
     * 为@Api的tags变量赋值
     *
     * @param apiMetadata     apiMetadata
     * @param jcExpressionMap jcExpressionMap
     */
    protected void addTags(ApiMetadata apiMetadata, Map<String, JCTree.JCExpression> jcExpressionMap) {
        if (CollUtil.isNotEmpty(apiMetadata.getTags())) {
            List<JCTree.JCLiteral> tags = apiMetadata.getTags().stream().map(this::createLiteral).collect(Collectors.toList());
            jcExpressionMap.put("tags", createNewArray(com.sun.tools.javac.util.List.from(tags)));
        }
    }

}
