package cn.dhbin.minion.core.swagger.plugin.adapter.impl;

import cn.dhbin.minion.core.swagger.plugin.adapter.ConvertAdapter;
import cn.dhbin.minion.core.swagger.plugin.adapter.ObtainNameHandler;
import cn.dhbin.minion.core.swagger.plugin.adapter.ParamDocHandler;
import cn.dhbin.minion.core.swagger.plugin.metadata.doc.DocInfo;
import com.sun.source.doctree.DocCommentTree;
import com.sun.tools.javac.tree.JCTree;

/**
 * @author donghaibin
 * @date 2020/8/6
 */
public class ComposeAdapterImpl implements ConvertAdapter<DocCommentTree, DocInfo>, ParamDocHandler, ObtainNameHandler {

    private final ConvertAdapter<DocCommentTree, DocInfo> docInfoAdapter = new DocInfoAdapterImpl();
    private final ParamDocHandler paramDocHandler = new ParamDocHandlerImpl();
    private final ObtainNameHandler obtainNameHandler = new ObtainNameHandlerImpl();

    @Override
    public DocInfo convert(DocCommentTree docCommentTree) {
        return docInfoAdapter.convert(docCommentTree);
    }


    @Override
    public void paramDocProcess(JCTree.JCMethodDecl methodDecl, DocInfo docInfo) {
        paramDocHandler.paramDocProcess(methodDecl, docInfo);
    }

    @Override
    public void obtainName(JCTree jcTree, DocInfo docInfo) {
        obtainNameHandler.obtainName(jcTree, docInfo);
    }

}
