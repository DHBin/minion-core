package cn.dhbin.minion.core.swagger.plugin.metadata.doc;

import java.util.List;

/**
 * 注解@param
 *
 * @author donghaibin
 * @date 2020/8/6
 */
public class ParamDoc extends BlockDoc {

    private String param;

    private String type;


    /**
     * 参数上的注解
     *
     * @since 1.0.2
     */
    private List<String> annotations;


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

    public List<String> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<String> annotations) {
        this.annotations = annotations;
    }

    @Override
    public String toString() {
        return "ParamDoc{" +
                "param='" + param + '\'' +
                ", type='" + type + '\'' +
                ", annotations=" + annotations +
                '}';
    }
}
