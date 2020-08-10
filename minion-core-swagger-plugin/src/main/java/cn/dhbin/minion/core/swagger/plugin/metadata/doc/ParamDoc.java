package cn.dhbin.minion.core.swagger.plugin.metadata.doc;

/**
 * 注解@param
 *
 * @author donghaibin
 * @date 2020/8/6
 */
public class ParamDoc extends BlockDoc {

    private String param;

    private String type;


    public ParamDoc(DocKind docKind) {
        super(docKind);
        setName("param");
    }

    public String getParam() {
        return param;
    }

    public void setParam(String name) {
        this.param = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getName() {
        return super.getName();
    }


    @Override
    public String toString() {
        return "ParamDoc{" +
                "param='" + param + '\'' +
                ", type='" + type + '\'' +
                "} " + super.toString();
    }
}
