package cn.dhbin.minion.core.swagger.plugin.javac;

import cn.dhbin.minion.core.swagger.plugin.Constant;
import cn.dhbin.minion.core.swagger.plugin.adapter.impl.ComposeAdapterImpl;
import cn.dhbin.minion.core.swagger.plugin.metadata.doc.DocInfo;
import cn.dhbin.minion.core.swagger.plugin.metadata.swagger.ApiModelMetadata;
import cn.dhbin.minion.core.swagger.plugin.spi.DocumentParser;
import cn.dhbin.minion.core.swagger.plugin.util.Services;
import cn.hutool.core.util.ObjectUtil;
import com.sun.source.doctree.DocCommentTree;
import com.sun.source.doctree.DocTree;
import com.sun.source.doctree.UnknownBlockTagTree;
import com.sun.source.tree.Tree;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;

import javax.lang.model.element.Element;
import java.util.HashMap;
import java.util.Map;

/**
 * @author donghaibin
 * @date 2020/8/7
 */
public class ApiModelTreeTranslator extends AbstractConditionTreeTranslator {

    private DocCommentTree docCommentTree;

    private final ComposeAdapterImpl docInfoAdapter = new ComposeAdapterImpl();

    private final DocumentParser documentParser = Services.getDocumentParser();


    public ApiModelTreeTranslator(JavacProcessingEnvironment environment, Element element) {
        super(environment, element);
    }

    @Override
    protected <T extends JCTree> T process(T tree) {
        DocInfo docInfo = docInfoAdapter.convert(docCommentTree);
        JCTree.JCClassDecl classDecl = (JCTree.JCClassDecl) tree;
        if (ObjectUtil.isNull(docInfo)) {
            return tree;
        }
        docInfoAdapter.obtainName(tree, docInfo);
        ApiModelMetadata metadata = documentParser.resolveApiModel(docInfo);
        if (ObjectUtil.isNull(metadata)) {
            return tree;
        }
        if (!existApiModelAnnotation(classDecl)) {
            Map<String, JCTree.JCExpression> params = new HashMap<>(16);
            params.put("value", createLiteral(metadata.getValue()));
            JCTree.JCAnnotation apiModelAnnotation = createAnnotation(Constant.SWAGGER_API_MODEL_CLASS_NAME, params);
            classDecl.getModifiers().annotations = classDecl.getModifiers().annotations.prepend(apiModelAnnotation);
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
        docCommentTree = getDocCommentTree(classDecl.sym);
        // 如果没有注释会为null
        if (docCommentTree == null) {
            return false;
        }
        for (DocTree blockTag : docCommentTree.getBlockTags()) {
            if (blockTag instanceof UnknownBlockTagTree) {
                if (Constant.API_MODEL_TAG.equals(((UnknownBlockTagTree) blockTag).getTagName())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean existApiModelAnnotation(JCTree.JCClassDecl tree) {
        com.sun.tools.javac.util.List<JCTree.JCAnnotation> annotations = tree.getModifiers().getAnnotations();
        for (JCTree.JCAnnotation annotation : annotations) {
            if (annotation.type != null && annotation.type.toString().equals(Constant.SWAGGER_API_MODEL_CLASS_NAME)) {
                return true;
            }
        }
        return false;
    }

}
