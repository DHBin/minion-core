package cn.dhbin.minion.core.swagger.plugin.metadata.swagger;

/**
 * swagger @ApiParam
 *
 * @author dhb
 */
public class ApiParamMetadata {

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getAllowableValues() {
        return allowableValues;
    }

    public void setAllowableValues(String allowableValues) {
        this.allowableValues = allowableValues;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public Boolean getAllowMultiple() {
        return allowMultiple;
    }

    public void setAllowMultiple(Boolean allowMultiple) {
        this.allowMultiple = allowMultiple;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Boolean getAllowEmptyValue() {
        return allowEmptyValue;
    }

    public void setAllowEmptyValue(Boolean allowEmptyValue) {
        this.allowEmptyValue = allowEmptyValue;
    }

    public Boolean getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
    }

    public String getCollectionFormat() {
        return collectionFormat;
    }

    public void setCollectionFormat(String collectionFormat) {
        this.collectionFormat = collectionFormat;
    }


}
