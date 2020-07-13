package cn.dhbin.minion.core.generate.config;

import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author donghaibin
 * @date 2020/7/13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MinionStrategyConfig extends StrategyConfig {

    /**
     * 是否使用SpringSecurity注解鉴权
     */
    private boolean springSecurityAnnotation;

}
