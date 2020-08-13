package cn.dhbin.minion.core.swagger.plugin.adapter.impl;

import cn.dhbin.minion.core.swagger.plugin.adapter.ParamDocHandler;
import cn.dhbin.minion.core.swagger.plugin.metadata.doc.Doc;
import cn.dhbin.minion.core.swagger.plugin.metadata.doc.DocInfo;
import cn.dhbin.minion.core.swagger.plugin.metadata.doc.ParamDoc;
import com.sun.tools.javac.tree.JCTree;

import java.util.stream.Collectors;

/**
 * @author donghaibin
 * @date 2020/8/6
 */
public class ParamDocHandlerImpl implements ParamDocHandler {

    @Override
    public void paramDocProcess(JCTree.JCMethodDecl methodDecl, DocInfo docInfo) {
        for (Doc tag : docInfo.getTags()) {
            if (!(tag instanceof ParamDoc)) {
                return;
            }
            ParamDoc paramDoc = (ParamDoc) tag;
            for (JCTree.JCVariableDecl parameter : methodDecl.getParameters()) {
                if (paramDoc.getParam().equals(parameter.getName().toString())) {
                    paramDoc.setType(parameter.getType().type.toString());
                    paramDoc.setAnnotations(parameter.getModifiers().getAnnotations().stream()
                            .map(jcAnnotation -> jcAnnotation.getAnnotationType().type.toString()).collect(Collectors.toList()));
                    break;
                }
            }
        }
    }
}
