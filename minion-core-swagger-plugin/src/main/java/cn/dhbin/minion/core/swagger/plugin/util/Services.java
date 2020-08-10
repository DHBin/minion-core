package cn.dhbin.minion.core.swagger.plugin.util;

import cn.dhbin.minion.core.swagger.plugin.spi.DocumentParser;
import cn.dhbin.minion.core.swagger.plugin.spi.impl.DefaultDocumentParserImpl;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * https://github.com/mapstruct/mapstruct/blob/master/processor/src/main/java/org/mapstruct/ap/internal/util/Services.java
 * <p>
 * A simple locator for SPI implementations.
 *
 * @author Christian Schuster
 * @author donghaibin
 * @date 2020/8/6
 */
public class Services {

    private static final DocumentParser DOCUMENT_PARSER = get(DocumentParser.class, new DefaultDocumentParserImpl());

    private Services() {
    }

    public static <T> T get(Class<T> serviceType, T defaultValue) {

        Iterator<T> services = ServiceLoader.load(serviceType, Services.class.getClassLoader()).iterator();

        T result;
        if (services.hasNext()) {
            result = services.next();
        } else {
            result = defaultValue;
        }
        if (services.hasNext()) {
            throw new IllegalStateException(
                    "Multiple implementations have been found for the service provider interface");
        }
        return result;
    }

    public static DocumentParser getDocumentParser() {
        return DOCUMENT_PARSER;
    }

}
