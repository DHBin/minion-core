package cn.dhbin.minion.core.swagger.plugin.adapter.impl;

import cn.dhbin.minion.core.swagger.plugin.adapter.ObtainNameHandler;
import cn.dhbin.minion.core.swagger.plugin.metadata.doc.DocInfo;
import com.sun.tools.javac.tree.JCTree;

/**
 * @author donghaibin
 * @date 2020/8/6
 */
public class ObtainNameHandlerImpl implements ObtainNameHandler {

    @Override
    public void obtainName(JCTree jcTree, DocInfo docInfo) {
        if (jcTree instanceof JCTree.JCClassDecl) {
            docInfo.setName(((JCTree.JCClassDecl) jcTree).name.toString());
        }

        if (jcTree instanceof JCTree.JCMethodDecl) {
            docInfo.setName(((JCTree.JCMethodDecl) jcTree).name.toString());
        }
    }
}
