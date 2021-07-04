package com.currency.common.config;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.currency.common.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class MyBatisMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldVal("createUser",SessionUtil.getSessionBean().getUserId(),metaObject);
        this.setFieldVal("createDate",new Date(),metaObject);
        this.setFieldVal("tenantId",SessionUtil.getSessionBean().getTenantId(),metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldVal("updateUser",SessionUtil.getSessionBean().getUserId(),metaObject);
        this.setFieldVal("updateDate",new Date(),metaObject);
    }
    private void setFieldVal(String filedName,Object filedVal,MetaObject metaObject){
        try{
            Object value = this.getFieldValByName(filedName,metaObject);
            if(value==null||(value instanceof String &&String.valueOf(value).length()<1)){
                this.setFieldValByName(filedName,filedVal,metaObject);
            }

        }catch (Exception e){
            log.error("设置："+filedName+"错误",e);
        }

    }
}
