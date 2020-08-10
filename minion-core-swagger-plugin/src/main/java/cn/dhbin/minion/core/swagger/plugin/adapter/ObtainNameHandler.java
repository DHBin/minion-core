package cn.dhbin.minion.core.swagger.plugin.adapter;

import cn.dhbin.minion.core.swagger.plugin.metadata.doc.DocInfo;
import com.sun.tools.javac.tree.JCTree;

/**
 * @author donghaibin
 * @date 2020/8/6
 */
@FunctionalInterface
public interface ObtainNameHandler {

    /**
     * 获取类名
     *
     * @param jcTree  tree
     * @param docInfo docInfo
     */
    void obtainName(JCTree jcTree, DocInfo docInfo);
}
