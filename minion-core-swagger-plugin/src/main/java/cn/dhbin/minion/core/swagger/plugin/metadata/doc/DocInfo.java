package cn.dhbin.minion.core.swagger.plugin.metadata.doc;

import java.util.List;

/**
 * @author donghaibin
 */
public class DocInfo {

    /**
     * 类名 或者 方法名
     */
    private String name;

    /**
     * 首句
     */
    private List<Doc> firstSentence;

    /**
     * body
     */
    private List<Doc> body;

    /**
     * tags
     */
    private List<Doc> tags;


    public List<Doc> getTags() {
        return tags;
    }

    public void setTags(List<Doc> tags) {
        this.tags = tags;
    }

    public List<Doc> getBody() {
        return body;
    }

    public void setBody(List<Doc> body) {
        this.body = body;
    }

    public List<Doc> getFirstSentence() {
        return firstSentence;
    }

    public void setFirstSentence(List<Doc> firstSentence) {
        this.firstSentence = firstSentence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DocInfo{" +
                "name='" + name + '\'' +
                ", firstSentence=" + firstSentence +
                ", body=" + body +
                ", tags=" + tags +
                '}';
    }
}
