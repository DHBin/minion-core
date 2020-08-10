package cn.dhbin.minion.core.swagger.plugin.metadata.doc;

/**
 * @author donghaibin
 * @date 2020/8/6
 */
public class BlockDoc extends CommonDoc {

    private String name;

    public BlockDoc(DocKind docKind) {
        super(docKind);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BlockDoc{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}
