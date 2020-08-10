package cn.dhbin.minion.core.swagger.plugin.adapter.impl;

import cn.dhbin.minion.core.swagger.plugin.adapter.ConvertAdapter;
import cn.dhbin.minion.core.swagger.plugin.metadata.doc.DocInfo;
import cn.dhbin.minion.core.swagger.plugin.metadata.doc.builder.DocInfoBuilder;
import com.sun.source.doctree.DocCommentTree;

import java.util.stream.Collectors;

/**
 * DocCommentTree to DocInfo
 *
 * @author donghaibin
 * @date 2020/8/6
 */
public class DocInfoAdapterImpl implements ConvertAdapter<DocCommentTree, DocInfo> {

    private final DocAdapterImpl docAdapter = new DocAdapterImpl();

    @Override
    public DocInfo convert(DocCommentTree docCommentTree) {
        DocInfoBuilder docInfoBuilder = DocInfoBuilder.aDocInfo();

        docInfoBuilder
                .firstSentence(
                        docCommentTree.getFirstSentence()
                                .stream().map(docAdapter::convert)
                                .collect(Collectors.toList())
                )
                .body(
                        docCommentTree.getBody().stream()
                                .map(docAdapter::convert)
                                .collect(Collectors.toList())
                )
                .tags(
                        docCommentTree.getBlockTags().stream()
                                .map(docAdapter::convert)
                                .collect(Collectors.toList())
                );
        return docInfoBuilder.build();
    }

}
