package cn.dhbin.minion.core.swagger.plugin.javac;

import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;

import javax.lang.model.element.Element;
import java.util.ArrayList;

/**
 * @author donghaibin
 * @date 2020/8/3
 */
public class CompilationUnitTranslator extends AbstractTreeTranslator {

    private final java.util.List<AbstractConditionTreeTranslator> translator = new ArrayList<>();

    public CompilationUnitTranslator(JavacProcessingEnvironment environment, Element element) {
        super(environment, element);
        translator.add(new ControllerTreeTranslator(environment, element));
        translator.add(new MappingTreeTranslator(environment, element));
        translator.add(new ApiModelTreeTranslator(environment, element));
        translator.add(new ApiModelPropertyTreeTranslator(environment, element));
    }

    @Override
    public <T extends JCTree> T translate(T tree) {
        for (AbstractConditionTreeTranslator treeTranslator : translator) {
            treeTranslator.translate(tree);
        }
        return super.translate(tree);
    }

}
