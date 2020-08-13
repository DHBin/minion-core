package cn.dhbin.minion.core.swagger.plugin.javac;

import cn.dhbin.minion.core.swagger.plugin.SwaggerProcessor;
import com.google.testing.compile.JavaFileObjects;
import org.junit.jupiter.api.Test;

import static com.google.testing.compile.JavaSourcesSubject.assertThat;

/**
 * @author donghaibin
 * @date 2020/8/13
 */
class ApiModelPropertyTreeTranslatorTest {

    @Test
    void testAddAnnotation() {
        assertThat(
                JavaFileObjects.forResource("test/Other.java"),
                JavaFileObjects.forResource("test/BaseApiModelBean.java"),
                JavaFileObjects.forResource("test/ControllerDemo.java")
        )
                .processedWith(new SwaggerProcessor())
                .compilesWithoutError();
    }
}