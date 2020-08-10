package cn.dhbin.minion.core.swagger.plugin.metadata.swagger.builder;

import cn.dhbin.minion.core.swagger.plugin.metadata.swagger.ApiModelMetadata;

import java.util.List;

/**
 * @author donghaibin
 * @date 2020/8/6
 */
public final class ApiModelMetadataBuilder {
    private String value;
    private String description;
    private Class<?> parent;
    private String discriminator;
    private List<Class<?>> subTypes;
    private String reference;

    private ApiModelMetadataBuilder() {
    }

    public static ApiModelMetadataBuilder anApiModelMetadata() {
        return new ApiModelMetadataBuilder();
    }

    public ApiModelMetadataBuilder value(String value) {
        this.value = value;
        return this;
    }

    public ApiModelMetadataBuilder description(String description) {
        this.description = description;
        return this;
    }

    public ApiModelMetadataBuilder parent(Class<?> parent) {
        this.parent = parent;
        return this;
    }

    public ApiModelMetadataBuilder discriminator(String discriminator) {
        this.discriminator = discriminator;
        return this;
    }

    public ApiModelMetadataBuilder subTypes(List<Class<?>> subTypes) {
        this.subTypes = subTypes;
        return this;
    }

    public ApiModelMetadataBuilder reference(String reference) {
        this.reference = reference;
        return this;
    }

    public ApiModelMetadata build() {
        ApiModelMetadata apiModelMetadata = new ApiModelMetadata();
        apiModelMetadata.setValue(value);
        apiModelMetadata.setDescription(description);
        apiModelMetadata.setParent(parent);
        apiModelMetadata.setDiscriminator(discriminator);
        apiModelMetadata.setSubTypes(subTypes);
        apiModelMetadata.setReference(reference);
        return apiModelMetadata;
    }
}
