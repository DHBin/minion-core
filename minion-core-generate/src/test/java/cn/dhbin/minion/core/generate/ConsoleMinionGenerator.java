package cn.dhbin.minion.core.generate;

import cn.dhbin.minion.core.generate.config.MinionGeneratorConfig;
import cn.dhbin.minion.core.generate.config.MinionGlobalConfig;
import cn.dhbin.minion.core.generate.config.MinionInjectionConfig;
import cn.dhbin.minion.core.generate.config.MinionStrategyConfig;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;

import java.util.Scanner;

/**
 * @author donghaibin
 * @date 2020/4/10
 */
public class ConsoleMinionGenerator {

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        String author = "DHB";
        // 全局配置
        GlobalConfig gc = MinionGeneratorConfig.globalConfig(author);
        mpg.setGlobalConfig(gc);


        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/minion_demo?useUnicode=true&useSSL=false&characterEncoding=utf8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        mpg.setDataSource(dsc);

        // 包配置
        String moduleName = scanner("模块名");
        PackageConfig pc = MinionGeneratorConfig.packageConfig("cn.dhbin.miniondemo", moduleName);
        mpg.setPackageInfo(pc);
        // 注入配置
        InjectionConfig cfg = new MinionInjectionConfig((MinionGlobalConfig) gc, pc);
        mpg.setCfg(cfg);
        // 配置模板
        TemplateConfig templateConfig = MinionGeneratorConfig.templateConfig();
        mpg.setTemplate(templateConfig);
        // 策略配置
        MinionStrategyConfig strategy = MinionGeneratorConfig.strategyConfig();
        // start 如果字段包含create_time、update_time、create_uid、update_uid，可以设置Entity父类为BaseEntity
        strategy.setSuperEntityClass("cn.dhbin.minion.core.common.entity.BaseEntity");
        strategy.setSuperEntityColumns("create_time", "update_time", "create_uid", "update_uid");
        // end
        // 使用Spring Security注解鉴权
        strategy.setSpringSecurityAnnotation(true);
        strategy.setInclude(scanner("表名").split(","));
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new MinionVelocityTemplateEngine());
        mpg.execute();
    }

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}
