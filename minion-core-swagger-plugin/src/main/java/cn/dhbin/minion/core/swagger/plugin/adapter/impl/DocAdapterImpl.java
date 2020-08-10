package cn.dhbin.minion.core.swagger.plugin.adapter.impl;

import cn.dhbin.minion.core.swagger.plugin.adapter.ConvertAdapter;
import cn.dhbin.minion.core.swagger.plugin.metadata.doc.*;
import cn.hutool.core.text.UnicodeUtil;
import com.sun.source.doctree.DocTree;
import com.sun.tools.javac.tree.DCTree;

/**
 * @author donghaibin
 * @date 2020/8/6
 */
public class DocAdapterImpl implements ConvertAdapter<DocTree, Doc> {


    @Override
    public Doc convert(DocTree docTree) {
        DocKind docKind = DocKind.valueOf(docTree.getKind().toString());
        if (docTree instanceof DCTree.DCParam) {
            DCTree.DCParam dcParam = (DCTree.DCParam) docTree;
            ParamDoc paramDoc = new ParamDoc(docKind);
            paramDoc.setParam(UnicodeUtil.toString(dcParam.name.toString()));
            paramDoc.setContent(UnicodeUtil.toString(dcParam.getDescription().toString()));
            return paramDoc;
        } else if (docTree instanceof DCTree.DCBlockTag) {
            DCTree.DCBlockTag blockTag = (DCTree.DCBlockTag) docTree;
            BlockDoc blockDoc = new BlockDoc(docKind);
            blockDoc.setName(UnicodeUtil.toString(blockTag.getTagName()));
            String content = blockTag.toString().substring(blockTag.getTagName().length() + 1).trim();
            blockDoc.setContent(UnicodeUtil.toString(content));
            return blockDoc;
        } else {
            CommonDoc doc = new CommonDoc(docKind);
            doc.setContent(UnicodeUtil.toString(docTree.toString()));
            return doc;
        }
    }
}
