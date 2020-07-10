package cn.dhbin.minion.core.mybatis.config;

import cn.dhbin.minion.core.common.IUserInfoProvider;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

import static cn.dhbin.minion.core.mybatis.constant.EntityConstant.*;

/**
 * 表基础信息填充
 *
 * @author donghaibin
 * @date 2019-08-12
 */
@RequiredArgsConstructor
public class DefaultMetaObjectHandler implements MetaObjectHandler {

    private final IUserInfoProvider userInfo;

    @Override
    public void insertFill(MetaObject metaObject) {
        final Long uid = userInfo.getUid();
        strictInsertFillIfAbsent(metaObject, CREATE_TIME, LocalDateTime.class, LocalDateTime.now());
        strictInsertFillIfAbsent(metaObject, CREATE_UID, Long.class, uid);
        strictInsertFillIfAbsent(metaObject, UPDATE_TIME, LocalDateTime.class, LocalDateTime.now());
        strictInsertFillIfAbsent(metaObject, UPDATE_UID, Long.class, uid);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        final Long uid = userInfo.getUid();
        strictUpdateFillIfAbsent(metaObject, UPDATE_TIME, LocalDateTime.class, LocalDateTime.now());
        strictUpdateFillIfAbsent(metaObject, UPDATE_UID, Long.class, uid);
    }

    private <T> void strictInsertFillIfAbsent(MetaObject metaObject, String fieldName, Class<T> fieldType, Object fieldVal) {
        if (metaObject.hasGetter(fieldName)) {
            strictInsertFill(metaObject, fieldName, fieldType, fieldVal);
        }
    }

    private <T> void strictUpdateFillIfAbsent(MetaObject metaObject, String fieldName, Class<T> fieldType, Object fieldVal) {
        if (metaObject.hasGetter(fieldName)) {
            strictUpdateFill(metaObject, fieldName, fieldType, fieldVal);
        }
    }

}
