package cn.dhbin.minion.core.swagger.plugin.javac;

import com.sun.source.doctree.DocCommentTree;
import com.sun.source.util.DocTrees;
import com.sun.source.util.TreePath;
import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import java.util.Map;

/**
 * @author donghaibin
 * @date 2020/8/3
 */
public class AbstractTreeTranslator extends TreeTranslator {

    protected final TreeMaker treeMaker;
    protected final Elements elements;
    protected final Names names;
    protected final JavacTrees javacTrees;
    protected final DocTrees docTrees;
    protected final Element element;
    protected final JavacProcessingEnvironment environment;


    public AbstractTreeTranslator(JavacProcessingEnvironment environment, Element element) {
        Context context = environment.getContext();
        this.treeMaker = TreeMaker.instance(context);
        this.elements = environment.getElementUtils();
        this.names = Names.instance(context);
        this.javacTrees = JavacTrees.instance(context);
        this.docTrees = DocTrees.instance(environment);
        this.element = element;
        this.environment = environment;
    }

    /**
     * 创建注解树
     *
     * @param annotationClass 注解类
     * @param jcExpressions   注解中的参数
     * @return 注解树
     */
    protected JCTree.JCAnnotation createAnnotation(String annotationClass, List<JCTree.JCExpression> jcExpressions) {
        TypeElement typeElement = elements.getTypeElement(annotationClass);
        TypeMirror typeMirror = typeElement.asType();
        return treeMaker.Annotation(treeMaker.Type((Type) typeMirror), jcExpressions);
    }

    /**
     * 创建注解树
     *
     * @param annotationClass 注解类
     * @param expressionMap   注解参数，key:value
     * @return 注解树
     */
    protected JCTree.JCAnnotation createAnnotation(String annotationClass, Map<String, JCTree.JCExpression> expressionMap) {
        JCTree.JCAssign[] jcAssigns = new JCTree.JCAssign[expressionMap.size()];
        int idx = 0;
        for (Map.Entry<String, JCTree.JCExpression> entry : expressionMap.entrySet()) {
            jcAssigns[idx] = createAssign(entry.getKey(), entry.getValue());
            idx++;
        }
        return createAnnotation(annotationClass, List.from(jcAssigns));
    }

    /**
     * 创建赋值语法树
     *
     * @param key          变量名
     * @param jcExpression 值的语法表达式
     * @return 赋值语法树
     */
    protected JCTree.JCAssign createAssign(String key, JCTree.JCExpression jcExpression) {
        Name name = names.fromString(key);
        return treeMaker.Assign(treeMaker.Ident(name), jcExpression);
    }

    /**
     * 创建 Literal
     *
     * @param value literal
     * @return Literal
     */
    protected JCTree.JCLiteral createLiteral(Object value) {
        return treeMaker.Literal(value);
    }

    /**
     * 创建 数组 语法树
     * <p>
     * 例子：
     * <p>
     * createNewArray(List.of(createLiteral("a"), createLiteral("b")))
     * <p>
     * 生成的代码 {"a", "b"}
     *
     * @param jcExpressions 表达式
     * @return 数组 语法树
     */
    protected JCTree.JCNewArray createNewArray(List<JCTree.JCExpression> jcExpressions) {
        return treeMaker.NewArray(null, List.nil(), jcExpressions);
    }

    /**
     * 整块javadoc
     *
     * @param element element
     * @return 整块javadoc
     */
    protected DocCommentTree getDocCommentTree(Element element) {
        TreePath treePath = javacTrees.getPath(element);
        return javacTrees.getDocCommentTree(treePath);
    }


}
