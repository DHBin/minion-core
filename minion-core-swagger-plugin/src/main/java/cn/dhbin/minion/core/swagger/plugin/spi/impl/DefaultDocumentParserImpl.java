package cn.dhbin.minion.core.swagger.plugin.spi.impl;

import cn.dhbin.minion.core.swagger.plugin.Constant;
import cn.dhbin.minion.core.swagger.plugin.metadata.doc.*;
import cn.dhbin.minion.core.swagger.plugin.metadata.swagger.*;
import cn.dhbin.minion.core.swagger.plugin.metadata.swagger.builder.*;
import cn.dhbin.minion.core.swagger.plugin.spi.DocumentParser;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author donghaibin
 * @date 2020/8/6
 */
public class DefaultDocumentParserImpl implements DocumentParser {

    /**
     * tags优先取
     *
     * @param docInfo controller类上的注释信息
     * @return {@link ApiModelMetadata}
     */
    @Override
    public ApiMetadata resolveApi(DocInfo docInfo) {
        if (isNull(docInfo)) {
            return null;
        }
        List<String> tags = resolveApiNotes(docInfo.getTags());
        ApiMetadataBuilder metadata = ApiMetadataBuilder.anApiMetadata();
        if (tags.isEmpty()) {
            metadata.tags(docInfo.getFirstSentence()
                    .stream()
                    .map(Doc::getContent)
                    .collect(Collectors.toList())
            );
        } else {
            metadata.tags(tags);
        }
        return metadata.build();
    }

    @Override
    public ApiOperationMetadata resolveApiOperation(DocInfo docInfo) {
        if (isNull(docInfo)) {
            return null;
        }
        ApiOperationMetadataBuilder builder = ApiOperationMetadataBuilder.anApiOperationMetadata();
        List<String> apiNotes = resolveApiNotes(docInfo.getTags());
        String value = String.join(StrUtil.EMPTY, apiNotes);
        // 如果apiNote为空，取首句
        if (StrUtil.isNotBlank(value)) {
            builder.value(value);
        } else {
            builder.value(docInfo.getFirstSentence().stream().map(Doc::getContent).collect(Collectors.joining(StrUtil.EMPTY)));
        }
        List<Doc> docs = new ArrayList<>();
        docs.addAll(docInfo.getFirstSentence());
        docs.addAll(docInfo.getBody());
        builder.notes(docs.stream().map(Doc::getContent).collect(Collectors.joining(StrUtil.EMPTY)));
        return builder.build();
    }

    @Override
    public List<ApiImplicitParamMetadata> resolveApiImplicitParam(DocInfo docInfo) {
        if (isNull(docInfo)) {
            return null;
        }
        List<ParamDoc> paramDocs = resolveParams(docInfo.getTags());
        return paramDocs.stream()
                .map(paramDoc -> {
                    // requestBody不加进ApiImplicitParams
                    if (paramDoc.getAnnotations().contains(Constant.SPRING_REQUEST_BODY_CLASS_NAME)) {
                        return null;
                    }
                    ApiImplicitParamMetadataBuilder builder = ApiImplicitParamMetadataBuilder.anApiImplicitParamMetadata();
                    builder.name(paramDoc.getParam())
                            .value(paramDoc.getContent())
                            .dataType(paramDoc.getType());
                    return builder.build();
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public ApiModelMetadata resolveApiModel(DocInfo docInfo) {
        if (ObjectUtil.isNull(docInfo)) {
            return null;
        }
        ApiModelMetadataBuilder builder = ApiModelMetadataBuilder.anApiModelMetadata();
        String value = docInfo.getTags().stream().filter(doc -> doc.getKind().equals(DocKind.UNKNOWN_BLOCK_TAG))
                .map(doc -> (BlockDoc) doc)
                .filter(blockDoc -> Constant.API_MODEL_TAG.equals(blockDoc.getName()))
                .map(CommonDoc::getContent)
                .collect(Collectors.joining());
        builder.value(value);
        return builder.build();
    }

    @Override
    public ApiModelPropertyMetadata resolveApiModelProperty(DocInfo docInfo) {
        if (ObjectUtil.isNull(docInfo)) {
            return null;
        }

        ApiModelPropertyMetadataBuilder builder = ApiModelPropertyMetadataBuilder.anApiModelPropertyMetadata();
        builder.value(docInfo.getFirstSentence().stream().map(Doc::getContent).collect(Collectors.joining()));

        return builder.build();
    }

    private List<String> resolveApiNotes(List<Doc> docs) {
        return docs.stream()
                .filter(doc -> doc instanceof BlockDoc)
                .filter(doc -> Constant.API_NOTE_TAG.equals(((BlockDoc) doc).getName()))
                .map(Doc::getContent)
                .collect(Collectors.toList());
    }

    private List<ParamDoc> resolveParams(List<Doc> docs) {
        return docs.stream()
                .filter(doc -> doc.getKind().equals(DocKind.PARAM))
                .map(doc -> (ParamDoc) doc)
                .collect(Collectors.toList());
    }

    private boolean isNull(Object obj) {
        return ObjectUtil.isNull(obj);
    }
}
