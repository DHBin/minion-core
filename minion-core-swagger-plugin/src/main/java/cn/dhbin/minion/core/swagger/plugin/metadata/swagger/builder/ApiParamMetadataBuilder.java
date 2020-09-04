package cn.dhbin.minion.core.swagger.plugin.metadata.swagger.builder;

import cn.dhbin.minion.core.swagger.plugin.metadata.swagger.ApiParamMetadata;

/**
 * @author dhb
 */
public final class ApiParamMetadataBuilder {
    private String name;
    private String value;
    private String defaultValue;
    private String allowableValues;
    private Boolean required;
    private String access;
    private Boolean allowMultiple;
    private Boolean hidden;
    private String example;
    private String type;
    private String format;
    private Boolean allowEmptyValue;
    private Boolean readOnly;
    private String collectionFormat;

    private ApiParamMetadataBuilder() {
    }

    public static ApiParamMetadataBuilder anApiParamMetadata() {
        return new ApiParamMetadataBuilder();
    }

    public ApiParamMetadataBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ApiParamMetadataBuilder value(String value) {
        this.value = value;
        return this;
    }

    public ApiParamMetadataBuilder defaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public ApiParamMetadataBuilder allowableValues(String allowableValues) {
        this.allowableValues = allowableValues;
        return this;
    }

    public ApiParamMetadataBuilder required(Boolean required) {
        this.required = required;
        return this;
    }

    public ApiParamMetadataBuilder access(String access) {
        this.access = access;
        return this;
    }

    public ApiParamMetadataBuilder allowMultiple(Boolean allowMultiple) {
        this.allowMultiple = allowMultiple;
        return this;
    }

    public ApiParamMetadataBuilder hidden(Boolean hidden) {
        this.hidden = hidden;
        return this;
    }

    public ApiParamMetadataBuilder example(String example) {
        this.example = example;
        return this;
    }

    public ApiParamMetadataBuilder type(String type) {
        this.type = type;
        return this;
    }

    public ApiParamMetadataBuilder format(String format) {
        this.format = format;
        return this;
    }

    public ApiParamMetadataBuilder allowEmptyValue(Boolean allowEmptyValue) {
        this.allowEmptyValue = allowEmptyValue;
        return this;
    }

    public ApiParamMetadataBuilder readOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    public ApiParamMetadataBuilder collectionFormat(String collectionFormat) {
        this.collectionFormat = collectionFormat;
        return this;
    }

    public ApiParamMetadata build() {
        ApiParamMetadata apiParamMetadata = new ApiParamMetadata();
        apiParamMetadata.setName(name);
        apiParamMetadata.setValue(value);
        apiParamMetadata.setDefaultValue(defaultValue);
        apiParamMetadata.setAllowableValues(allowableValues);
        apiParamMetadata.setRequired(required);
        apiParamMetadata.setAccess(access);
        apiParamMetadata.setAllowMultiple(allowMultiple);
        apiParamMetadata.setHidden(hidden);
        apiParamMetadata.setExample(example);
        apiParamMetadata.setType(type);
        apiParamMetadata.setFormat(format);
        apiParamMetadata.setAllowEmptyValue(allowEmptyValue);
        apiParamMetadata.setReadOnly(readOnly);
        apiParamMetadata.setCollectionFormat(collectionFormat);
        return apiParamMetadata;
    }

}
