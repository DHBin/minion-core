package cn.dhbin.minion.core.generate.config;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;

/**
 * @author donghaibin
 * @date 2020/7/10
 */
public class SqlDataFileOutConfig extends FileOutConfig {

    private final MinionGlobalConfig globalConfig;

    private final PackageConfig packageConfig;

    public SqlDataFileOutConfig(MinionGlobalConfig globalConfig, PackageConfig packageConfig) {
        this.globalConfig = globalConfig;
        this.packageConfig = packageConfig;
        setTemplatePath("templates/data.sql.vm");
    }

    @Override
    public String outputFile(TableInfo tableInfo) {
        return globalConfig.getSqlPath() + "/" + packageConfig.getModuleName()
                + "/" + StrUtil.lowerFirst(tableInfo.getEntityName()) + "-menu-data.sql";
    }

}
