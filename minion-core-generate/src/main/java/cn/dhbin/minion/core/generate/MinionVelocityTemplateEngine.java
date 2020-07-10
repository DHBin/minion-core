package cn.dhbin.minion.core.generate;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * @author donghaibin
 * @date 2020/3/28
 */
public class MinionVelocityTemplateEngine extends VelocityTemplateEngine {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

    @Override
    public Map<String, Object> getObjectMap(TableInfo tableInfo) {
        Map<String, Object> objectMap = super.getObjectMap(tableInfo);
        // 首字母小写的Entity名称
        objectMap.put("firstLowerEntityName", StrUtil.lowerFirst(tableInfo.getEntityName()));
        // 首字母小写的服务名
        objectMap.put("firstLowerServiceName", StrUtil.lowerFirst(tableInfo.getServiceName()));
        // 当前时间 yyyy-MM-dd hh:mm:ss
        objectMap.put("currentTime", formatter.format(LocalDateTime.now()));
        return objectMap;
    }

}
