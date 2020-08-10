package cn.dhbin.minion.core.swagger.plugin.javac;

import cn.dhbin.minion.core.swagger.plugin.Constant;
import cn.dhbin.minion.core.swagger.plugin.adapter.impl.ComposeAdapterImpl;
import cn.dhbin.minion.core.swagger.plugin.metadata.doc.DocInfo;
import cn.dhbin.minion.core.swagger.plugin.metadata.swagger.ApiModelPropertyMetadata;
import cn.dhbin.minion.core.swagger.plugin.spi.DocumentParser;
import cn.dhbin.minion.core.swagger.plugin.util.Services;
import cn.hutool.core.util.ObjectUtil;
import com.sun.source.doctree.DocCommentTree;
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
public class ApiModelPropertyTreeTranslator extends AbstractConditionTreeTranslator {

    private final ComposeAdapterImpl docInfoAdapter = new ComposeAdapterImpl();

    private final DocumentParser documentParser = Services.getDocumentParser();

    public ApiModelPropertyTreeTranslator(JavacProcessingEnvironment environment, Element element) {
        super(environment, element);
    }

    @Override
    protected <T extends JCTree> T process(T tree) {
        JCTree.JCVariableDecl variableDecl = (JCTree.JCVariableDecl) tree;
        if (variableDecl.sym == null) {
            return tree;
        }
        DocCommentTree docCommentTree = getDocCommentTree(variableDecl.sym);
        if (ObjectUtil.isNull(docCommentTree)) {
            return tree;
        }
        DocInfo docInfo = docInfoAdapter.convert(docCommentTree);
        docInfoAdapter.obtainName(tree, docInfo);

        ApiModelPropertyMetadata metadata = documentParser.resolveApiModelProperty(docInfo);
        if (ObjectUtil.isNull(metadata)) {
            return tree;
        }
        if (!existApiModelPropertyAnnotation(variableDecl)) {
            Map<String, JCTree.JCExpression> params = new HashMap<>(16);
            params.put("value", createLiteral(metadata.getValue()));
            JCTree.JCAnnotation apiModelAnnotation = createAnnotation(Constant.SWAGGER_API_MODEL_PROPERTY_CLASS_NAME, params);
            variableDecl.getModifiers().annotations = variableDecl.getModifiers().annotations.prepend(apiModelAnnotation);
        }
        return tree;
    }

    @Override
    public boolean test(JCTree jcTree) {
        try {
            return jcTree != null && jcTree.getKind() != null && jcTree.getKind().equals(Tree.Kind.VARIABLE);
        } catch (AssertionError e) {
            return false;
        }
    }

    private boolean existApiModelPropertyAnnotation(JCTree.JCVariableDecl tree) {
        com.sun.tools.javac.util.List<JCTree.JCAnnotation> annotations = tree.getModifiers().getAnnotations();
        for (JCTree.JCAnnotation annotation : annotations) {
            if (annotation.type != null && annotation.type.toString().equals(Constant.SWAGGER_API_MODEL_PROPERTY_CLASS_NAME)) {
                return true;
            }
        }
        return false;
    }
}
