package com.currency.common.mybatis;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.currency.common.bean.ExecuteCode;
import com.currency.constrants.CommonConstrants;
import com.currency.utils.LoginContextUtil;
import com.currency.utils.ObjectUtil;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collection;

public class BaseServiceImpl<M extends BaseMapper<T>, T, D> extends ServiceImpl<M, T> {

    @Override
    public T getById(Serializable id) {
        T t = baseMapper.selectById(id);
        if (t != null) {
            String statusCd = (String) ObjectUtil.invokeSimple(t, "getStatusCd", null, null);
            if (!CommonConstrants.COMMON_YES.equals(statusCd)) {
                return null;
            }
        }
        return t;

    }

    public D getDtoById(Serializable id, Class<D> dtoClass) {
        T t = this.getById(id);
        return ObjectUtil.copy(t, dtoClass);

    }

    /**
     * 伪删除
     *
     * @param id
     */
    public void delHadById(Serializable id) {
        delHadById(id, null);
    }

    /**
     * 伪删除
     *
     * @param id
     */
    public void delHadById(Serializable id, ExecuteCode executeCode) {
        T t = this.getById(id);
        if (t == null) {
            return;
        }
        LoginContextUtil.dealDel(t);
        if (executeCode != null) {
            executeCode.before(t);
        }
        this.save(t);
        if (executeCode != null) {
            executeCode.after(t);
        }
    }

    public void delHadByIds(Collection<? extends Serializable> idList) {
        delHadByIds(idList, null);
    }

    public void delHadByIds(Collection<? extends Serializable> idList, ExecuteCode executeCode) {
        if (!CollectionUtils.isEmpty(idList)) {
            for (Serializable serializable : idList) {
                delHadById(serializable, executeCode);
            }
        }
    }

}
