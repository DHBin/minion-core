package cn.dhbin.minion.core.swagger.plugin.adapter;

/**
 * @author donghaibin
 * @date 2020/8/6
 */
public interface ConvertAdapter<From, To> {

    /**
     * form to To
     *
     * @param from 原类型
     * @return 转换后类型
     */
    To convert(From from);


}
