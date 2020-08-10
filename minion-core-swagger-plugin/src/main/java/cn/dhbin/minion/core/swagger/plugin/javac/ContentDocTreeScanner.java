package cn.dhbin.minion.core.swagger.plugin.javac;

import cn.hutool.core.util.StrUtil;
import com.sun.source.doctree.DocTree;
import com.sun.source.util.DocTreeScanner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author donghaibin
 * @date 2020/8/4
 */
public class ContentDocTreeScanner extends DocTreeScanner<String, Void> {

    @Override
    public String scan(DocTree node, Void unused) {
        return node.toString();
    }

    @Override
    public String scan(Iterable<? extends DocTree> nodes, Void unused) {
        List<String> tmp = new ArrayList<>();
        for (DocTree node : nodes) {
            tmp.add(node.toString());
        }
        return String.join(StrUtil.EMPTY, tmp);
    }

    @Override
    public String reduce(String r1, String r2) {
        return r1 + r2;
    }

}
