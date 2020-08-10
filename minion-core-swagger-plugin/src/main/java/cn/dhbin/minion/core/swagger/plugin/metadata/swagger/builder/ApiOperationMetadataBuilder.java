package cn.dhbin.minion.core.swagger.plugin.metadata.swagger.builder;

import cn.dhbin.minion.core.swagger.plugin.metadata.swagger.ApiOperationMetadata;

import java.util.List;

/**
 * @author donghaibin
 * @date 2020/8/6
 */
public final class ApiOperationMetadataBuilder {

    private String value;
    private String notes;
    private List<String> tags;
    private String nickname;
    private String produces;
    private String consumes;
    private String protocols;
    private List<String> authorizations;
    private Integer code;

    private ApiOperationMetadataBuilder() {
    }

    public static ApiOperationMetadataBuilder anApiOperationMetadata() {
        return new ApiOperationMetadataBuilder();
    }

    public ApiOperationMetadataBuilder value(String value) {
        this.value = value;
        return this;
    }

    public ApiOperationMetadataBuilder notes(String notes) {
        this.notes = notes;
        return this;
    }

    public ApiOperationMetadataBuilder tags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public ApiOperationMetadataBuilder nickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ApiOperationMetadataBuilder produces(String produces) {
        this.produces = produces;
        return this;
    }

    public ApiOperationMetadataBuilder consumes(String consumes) {
        this.consumes = consumes;
        return this;
    }

    public ApiOperationMetadataBuilder protocols(String protocols) {
        this.protocols = protocols;
        return this;
    }

    public ApiOperationMetadataBuilder authorizations(List<String> authorizations) {
        this.authorizations = authorizations;
        return this;
    }

    public ApiOperationMetadataBuilder code(Integer code) {
        this.code = code;
        return this;
    }

    public ApiOperationMetadata build() {
        ApiOperationMetadata apiOperationMetadata = new ApiOperationMetadata();
        apiOperationMetadata.setValue(value);
        apiOperationMetadata.setNotes(notes);
        apiOperationMetadata.setTags(tags);
        apiOperationMetadata.setNickname(nickname);
        apiOperationMetadata.setProduces(produces);
        apiOperationMetadata.setConsumes(consumes);
        apiOperationMetadata.setProtocols(protocols);
        apiOperationMetadata.setAuthorizations(authorizations);
        apiOperationMetadata.setCode(code);
        return apiOperationMetadata;
    }
}
