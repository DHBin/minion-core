package cn.dhbin.minion.core.swagger.plugin;

import cn.dhbin.minion.core.swagger.plugin.javac.CompilationUnitTranslator;
import com.google.auto.service.AutoService;
import com.sun.source.util.TreePath;
import com.sun.source.util.Trees;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * @author donghaibin
 * @date 2020/8/3
 */
@SupportedAnnotationTypes(value = "*")
@AutoService(Processor.class)
public class SwaggerProcessor extends AbstractProcessor {

    private Trees trees;

    private boolean isJavac = false;

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        if (processingEnv instanceof JavacProcessingEnvironment) {
            JavacProcessingEnvironment javacProcessingEnv = (JavacProcessingEnvironment) processingEnv;
            trees = Trees.instance(javacProcessingEnv);
            isJavac = true;
        }
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!isJavac) {
            return false;
        }
        Set<? extends Element> rootElements = roundEnv.getRootElements();
        for (Element rootElement : rootElements) {
            JCTree.JCCompilationUnit jcCompilationUnit = toUnit(rootElement);
            if (jcCompilationUnit != null) {
                JavacProcessingEnvironment env = (JavacProcessingEnvironment) this.processingEnv;
                CompilationUnitTranslator compilationUnitTranslator = new CompilationUnitTranslator(env, rootElement);
                jcCompilationUnit.accept(compilationUnitTranslator);
            }
        }
        return false;
    }

    private JCTree.JCCompilationUnit toUnit(Element element) {
        TreePath path = null;
        if (trees != null) {
            try {
                path = trees.getPath(element);
            } catch (NullPointerException ignore) {
            }
        }
        if (path == null) {
            return null;
        }

        return (JCTree.JCCompilationUnit) path.getCompilationUnit();
    }

}
