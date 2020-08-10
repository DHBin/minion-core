package cn.dhbin.minion.core.swagger.plugin.metadata.doc;

import java.io.Serializable;

/**
 * @author donghaibin
 * @date 2020/8/6
 */
public interface Doc extends Serializable {

    /**
     * 类型
     *
     * @return {@link DocKind}
     */
    DocKind getKind();

    /**
     * 内容
     *
     * @return 内容
     */
    String getContent();


}
