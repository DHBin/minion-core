package cn.dhbin.minion.core.swagger.plugin.metadata.swagger.builder;

import cn.dhbin.minion.core.swagger.plugin.metadata.swagger.ApiMetadata;

import java.util.List;

/**
 * @author donghaibin
 * @date 2020/8/6
 */
public final class ApiMetadataBuilder {
    private String value;
    private List<String> tags;
    private String produces;
    private String consumes;
    private String protocols;
    private List<String> authorizations;
    private Boolean hidden;

    private ApiMetadataBuilder() {
    }

    public static ApiMetadataBuilder anApiMetadata() {
        return new ApiMetadataBuilder();
    }

    public ApiMetadataBuilder value(String value) {
        this.value = value;
        return this;
    }

    public ApiMetadataBuilder tags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public ApiMetadataBuilder produces(String produces) {
        this.produces = produces;
        return this;
    }

    public ApiMetadataBuilder consumes(String consumes) {
        this.consumes = consumes;
        return this;
    }

    public ApiMetadataBuilder protocols(String protocols) {
        this.protocols = protocols;
        return this;
    }

    public ApiMetadataBuilder authorizations(List<String> authorizations) {
        this.authorizations = authorizations;
        return this;
    }

    public ApiMetadataBuilder hidden(Boolean hidden) {
        this.hidden = hidden;
        return this;
    }

    public ApiMetadata build() {
        ApiMetadata apiMetadata = new ApiMetadata();
        apiMetadata.setValue(value);
        apiMetadata.setTags(tags);
        apiMetadata.setProduces(produces);
        apiMetadata.setConsumes(consumes);
        apiMetadata.setProtocols(protocols);
        apiMetadata.setAuthorizations(authorizations);
        apiMetadata.setHidden(hidden);
        return apiMetadata;
    }
}
