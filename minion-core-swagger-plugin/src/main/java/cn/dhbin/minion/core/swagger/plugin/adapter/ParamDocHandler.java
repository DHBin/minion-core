package cn.dhbin.minion.core.swagger.plugin.adapter;

import cn.dhbin.minion.core.swagger.plugin.metadata.doc.DocInfo;
import com.sun.tools.javac.tree.JCTree;

/**
 * @author donghaibin
 * @date 2020/8/6
 */
@FunctionalInterface
public interface ParamDocHandler {

    /**
     * 处理docInfo.tags中的@param
     *
     * @param jcMethodDecl method
     * @param docInfo      docInfo
     */
    void paramDocProcess(JCTree.JCMethodDecl jcMethodDecl, DocInfo docInfo);

}
