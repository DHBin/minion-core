package cn.dhbin.minion.core.swagger.plugin.metadata.doc;

/**
 * Javadoc
 *
 * @author donghaibin
 * @date 2020/8/6
 */
public class CommonDoc implements Doc {

    private final DocKind docKind;

    private String content;

    public CommonDoc(DocKind docKind) {
        this.docKind = docKind;
    }

    @Override
    public DocKind getKind() {
        return this.docKind;
    }

    @Override
    public String getContent() {
        return this.content;
    }

    public DocKind getDocKind() {
        return docKind;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "JavaDoc{" +
                "docKind=" + docKind +
                ", content='" + content + '\'' +
                '}';
    }
}
