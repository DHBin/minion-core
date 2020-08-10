package cn.dhbin.minion.core.swagger.plugin.spi;

import cn.dhbin.minion.core.swagger.plugin.metadata.doc.DocInfo;
import cn.dhbin.minion.core.swagger.plugin.metadata.swagger.*;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author donghaibin
 * @date 2020/8/6
 */
public interface DocumentParser {

    /**
     * 解析生成@Api
     *
     * @param docInfo controller类上的注释信息
     * @return {@link ApiMetadata}
     */
    @Nullable
    default ApiMetadata resolveApi(@Nullable DocInfo docInfo) {
        return null;
    }

    /**
     * 解析生成@ApiOperation
     *
     * @param docInfo 带mapping注解的方法上的注释信息
     * @return {@link ApiOperationMetadata}
     */
    @Nullable
    default ApiOperationMetadata resolveApiOperation(@Nullable DocInfo docInfo) {
        return null;
    }

    /**
     * 解析生成@ApiImplicitParams
     *
     * @param docInfo 带mapping注解的方法上的注释信息
     * @return {@link ApiImplicitParamMetadata}
     */
    @Nullable
    default List<ApiImplicitParamMetadata> resolveApiImplicitParam(@Nullable DocInfo docInfo) {
        return null;
    }

    /**
     * 解析生成@ApiModel
     *
     * @param docInfo bean类的的注释信息
     * @return {@link ApiModelMetadata}
     */
    @Nullable
    default ApiModelMetadata resolveApiModel(@Nullable DocInfo docInfo) {
        return null;
    }


    /**
     * 解析生成@ApiModelProperty
     *
     * @param docInfo bean类的方法的注释信息
     * @return {@link ApiModelPropertyMetadata}
     */
    @Nullable
    default ApiModelPropertyMetadata resolveApiModelProperty(@Nullable DocInfo docInfo) {
        return null;
    }

}
