package cn.dhbin.minion.core.swagger.plugin.metadata.swagger;

import java.util.List;

/**
 * @author donghaibin
 * @date 2020/8/6
 * <p>
 * io.swagger.annotations.Api
 */
public class ApiMetadata {

    private String value;

    private List<String> tags;

    private String produces;

    private String consumes;

    private String protocols;

    private List<String> authorizations;

    private Boolean hidden;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getProduces() {
        return produces;
    }

    public void setProduces(String produces) {
        this.produces = produces;
    }

    public String getConsumes() {
        return consumes;
    }

    public void setConsumes(String consumes) {
        this.consumes = consumes;
    }

    public String getProtocols() {
        return protocols;
    }

    public void setProtocols(String protocols) {
        this.protocols = protocols;
    }

    public List<String> getAuthorizations() {
        return authorizations;
    }

    public void setAuthorizations(List<String> authorizations) {
        this.authorizations = authorizations;
    }


    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
