package cn.dhbin.minion.core.swagger.plugin.metadata.doc.builder;

import cn.dhbin.minion.core.swagger.plugin.metadata.doc.Doc;
import cn.dhbin.minion.core.swagger.plugin.metadata.doc.DocInfo;

import java.util.List;

/**
 * @author donghaibin
 * @date 2020/8/6
 */
public final class DocInfoBuilder {
    private String name;
    private List<Doc> firstSentence;
    private List<Doc> body;
    private List<Doc> tags;

    private DocInfoBuilder() {
    }

    public static DocInfoBuilder aDocInfo() {
        return new DocInfoBuilder();
    }

    public DocInfoBuilder name(String name) {
        this.name = name;
        return this;
    }

    public DocInfoBuilder firstSentence(List<Doc> firstSentence) {
        this.firstSentence = firstSentence;
        return this;
    }

    public DocInfoBuilder body(List<Doc> body) {
        this.body = body;
        return this;
    }

    public DocInfoBuilder tags(List<Doc> tags) {
        this.tags = tags;
        return this;
    }

    public DocInfo build() {
        DocInfo docInfo = new DocInfo();
        docInfo.setName(name);
        docInfo.setFirstSentence(firstSentence);
        docInfo.setBody(body);
        docInfo.setTags(tags);
        return docInfo;
    }
}
