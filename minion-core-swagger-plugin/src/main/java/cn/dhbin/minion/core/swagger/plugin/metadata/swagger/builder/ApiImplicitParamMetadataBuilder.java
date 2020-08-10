package cn.dhbin.minion.core.swagger.plugin.metadata.swagger.builder;

import cn.dhbin.minion.core.swagger.plugin.metadata.swagger.ApiImplicitParamMetadata;

/**
 * @author donghaibin
 * @date 2020/8/6
 */
public final class ApiImplicitParamMetadataBuilder {

    private String name;
    private String value;
    private String defaultValue;
    private String allowableValues;
    private Boolean required;
    private String access;
    private Boolean allowMultiple;
    private String dataType;
    private Class<?> dataTypeClass;
    private String paramType;
    private String example;
    private String type;
    private String format;
    private Boolean allowEmptyValue;
    private Boolean readOnly;
    private String collectionFormat;

    private ApiImplicitParamMetadataBuilder() {
    }

    public static ApiImplicitParamMetadataBuilder anApiImplicitParamMetadata() {
        return new ApiImplicitParamMetadataBuilder();
    }

    public ApiImplicitParamMetadataBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ApiImplicitParamMetadataBuilder value(String value) {
        this.value = value;
        return this;
    }

    public ApiImplicitParamMetadataBuilder defaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public ApiImplicitParamMetadataBuilder allowableValues(String allowableValues) {
        this.allowableValues = allowableValues;
        return this;
    }

    public ApiImplicitParamMetadataBuilder required(Boolean required) {
        this.required = required;
        return this;
    }

    public ApiImplicitParamMetadataBuilder access(String access) {
        this.access = access;
        return this;
    }

    public ApiImplicitParamMetadataBuilder allowMultiple(Boolean allowMultiple) {
        this.allowMultiple = allowMultiple;
        return this;
    }

    public ApiImplicitParamMetadataBuilder dataType(String dataType) {
        this.dataType = dataType;
        return this;
    }

    public ApiImplicitParamMetadataBuilder dataTypeClass(Class<?> dataTypeClass) {
        this.dataTypeClass = dataTypeClass;
        return this;
    }

    public ApiImplicitParamMetadataBuilder paramType(String paramType) {
        this.paramType = paramType;
        return this;
    }

    public ApiImplicitParamMetadataBuilder example(String example) {
        this.example = example;
        return this;
    }

    public ApiImplicitParamMetadataBuilder type(String type) {
        this.type = type;
        return this;
    }

    public ApiImplicitParamMetadataBuilder format(String format) {
        this.format = format;
        return this;
    }

    public ApiImplicitParamMetadataBuilder allowEmptyValue(Boolean allowEmptyValue) {
        this.allowEmptyValue = allowEmptyValue;
        return this;
    }

    public ApiImplicitParamMetadataBuilder readOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    public ApiImplicitParamMetadataBuilder collectionFormat(String collectionFormat) {
        this.collectionFormat = collectionFormat;
        return this;
    }

    public ApiImplicitParamMetadata build() {
        ApiImplicitParamMetadata apiImplicitParamMetadata = new ApiImplicitParamMetadata();
        apiImplicitParamMetadata.setName(name);
        apiImplicitParamMetadata.setValue(value);
        apiImplicitParamMetadata.setDefaultValue(defaultValue);
        apiImplicitParamMetadata.setAllowableValues(allowableValues);
        apiImplicitParamMetadata.setRequired(required);
        apiImplicitParamMetadata.setAccess(access);
        apiImplicitParamMetadata.setAllowMultiple(allowMultiple);
        apiImplicitParamMetadata.setDataType(dataType);
        apiImplicitParamMetadata.setDataTypeClass(dataTypeClass);
        apiImplicitParamMetadata.setParamType(paramType);
        apiImplicitParamMetadata.setExample(example);
        apiImplicitParamMetadata.setType(type);
        apiImplicitParamMetadata.setFormat(format);
        apiImplicitParamMetadata.setAllowEmptyValue(allowEmptyValue);
        apiImplicitParamMetadata.setReadOnly(readOnly);
        apiImplicitParamMetadata.setCollectionFormat(collectionFormat);
        return apiImplicitParamMetadata;
    }
}
