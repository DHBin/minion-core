package cn.dhbin.minion.core.swagger.plugin.metadata.swagger;

import java.util.List;

/**
 * @author donghaibin
 * @date 2020/8/6
 */
public class ApiModelMetadata {

    private String value;

    private String description;

    private Class<?> parent;

    private String discriminator;

    private List<Class<?>> subTypes;

    private String reference;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Class<?> getParent() {
        return parent;
    }

    public void setParent(Class<?> parent) {
        this.parent = parent;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

    public List<Class<?>> getSubTypes() {
        return subTypes;
    }

    public void setSubTypes(List<Class<?>> subTypes) {
        this.subTypes = subTypes;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
