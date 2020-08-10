package cn.dhbin.minion.core.swagger.plugin.metadata.swagger.builder;

import cn.dhbin.minion.core.swagger.plugin.metadata.swagger.ApiModelPropertyMetadata;

/**
 * @author donghaibin
 * @date 2020/8/6
 */
public final class ApiModelPropertyMetadataBuilder {
    private String value;
    private String name;
    private String allowableValues;
    private String access;
    private String notes;
    private String dataType;
    private Boolean required;
    private Integer position;
    private Boolean hidden;
    private String example;
    private String reference;
    private Boolean allowEmptyValue;

    private ApiModelPropertyMetadataBuilder() {
    }

    public static ApiModelPropertyMetadataBuilder anApiModelPropertyMetadata() {
        return new ApiModelPropertyMetadataBuilder();
    }

    public ApiModelPropertyMetadataBuilder value(String value) {
        this.value = value;
        return this;
    }

    public ApiModelPropertyMetadataBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ApiModelPropertyMetadataBuilder allowableValues(String allowableValues) {
        this.allowableValues = allowableValues;
        return this;
    }

    public ApiModelPropertyMetadataBuilder access(String access) {
        this.access = access;
        return this;
    }

    public ApiModelPropertyMetadataBuilder notes(String notes) {
        this.notes = notes;
        return this;
    }

    public ApiModelPropertyMetadataBuilder dataType(String dataType) {
        this.dataType = dataType;
        return this;
    }

    public ApiModelPropertyMetadataBuilder required(Boolean required) {
        this.required = required;
        return this;
    }

    public ApiModelPropertyMetadataBuilder position(Integer position) {
        this.position = position;
        return this;
    }

    public ApiModelPropertyMetadataBuilder hidden(Boolean hidden) {
        this.hidden = hidden;
        return this;
    }

    public ApiModelPropertyMetadataBuilder example(String example) {
        this.example = example;
        return this;
    }

    public ApiModelPropertyMetadataBuilder reference(String reference) {
        this.reference = reference;
        return this;
    }

    public ApiModelPropertyMetadataBuilder allowEmptyValue(Boolean allowEmptyValue) {
        this.allowEmptyValue = allowEmptyValue;
        return this;
    }

    public ApiModelPropertyMetadata build() {
        ApiModelPropertyMetadata apiModelPropertyMetadata = new ApiModelPropertyMetadata();
        apiModelPropertyMetadata.setValue(value);
        apiModelPropertyMetadata.setName(name);
        apiModelPropertyMetadata.setAllowableValues(allowableValues);
        apiModelPropertyMetadata.setAccess(access);
        apiModelPropertyMetadata.setNotes(notes);
        apiModelPropertyMetadata.setDataType(dataType);
        apiModelPropertyMetadata.setRequired(required);
        apiModelPropertyMetadata.setPosition(position);
        apiModelPropertyMetadata.setHidden(hidden);
        apiModelPropertyMetadata.setExample(example);
        apiModelPropertyMetadata.setReference(reference);
        apiModelPropertyMetadata.setAllowEmptyValue(allowEmptyValue);
        return apiModelPropertyMetadata;
    }
}
