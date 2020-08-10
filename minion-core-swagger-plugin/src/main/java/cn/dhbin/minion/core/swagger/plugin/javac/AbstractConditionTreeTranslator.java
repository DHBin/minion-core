package cn.dhbin.minion.core.swagger.plugin.javac;

import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;

import javax.lang.model.element.Element;
import java.util.function.Predicate;

/**
 * 条件执行
 *
 * @author donghaibin
 * @date 2020/8/6
 */
public abstract class AbstractConditionTreeTranslator extends AbstractTreeTranslator implements Predicate<JCTree> {

    public AbstractConditionTreeTranslator(JavacProcessingEnvironment environment, Element element) {
        super(environment, element);
    }

    @Override
    public <T extends JCTree> T translate(T tree) {
        return test(tree) ? process(tree) : tree;
    }

    /**
     * 处理
     *
     * @param tree tree
     * @param <T>  tree类型
     * @return tree
     */
    protected abstract <T extends JCTree> T process(T tree);

}
