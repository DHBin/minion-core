package cn.dhbin.minion.core.mybatis.service;

import cn.dhbin.minion.core.mybatis.mapper.MinionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;

import java.io.Serializable;

/**
 * @author donghaibin
 * @date 2019-08-14
 */
public class MinionServiceImpl<M extends MinionMapper<T>, T extends Serializable> extends ServiceImpl<M, T> implements IMinionService<T> {


    @Override
    public T updateByIdAndReturn(T entity) {
        boolean retBool = SqlHelper.retBool(getBaseMapper().updateById(entity));
        return retBool ? getBaseMapper().selectById(entity) : null;
    }

}
