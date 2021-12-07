package com.currency.common.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.currency.dto.PageDTO;
import com.currency.sys.mapper.CommonSqlMapper;
import com.currency.utils.SpringUtil;
import com.currency.utils.UUIDUtils;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class NativeSqlUtil {

    private static ThreadLocal<Map<String, Map<String, Object>>> paramThreadLocal = new ThreadLocal<Map<String, Map<String, Object>>>();

    /**
     * 执行删除SQL
     *
     * @param sql
     * @param params
     * @return
     */
    public static int delete(String sql, Object params) {
        CommonSqlMapper commonSqlMapper = SpringUtil.getBean(CommonSqlMapper.class);
        return commonSqlMapper.delete(tranParam(sql, params));
    }

    /**
     * 执行UPDATE
     *
     * @param sql
     * @param params
     * @return
     */
    public static int update(String sql, Object params) {
        CommonSqlMapper commonSqlMapper = SpringUtil.getBean(CommonSqlMapper.class);
        return commonSqlMapper.update(tranParam(sql, params));
    }

    /**
     * 执行insert
     *
     * @param sql
     * @param params
     * @return
     */
    public static int insert(String sql, Object params) {
        CommonSqlMapper commonSqlMapper = SpringUtil.getBean(CommonSqlMapper.class);
        return commonSqlMapper.insert(tranParam(sql, params));
    }


    public static <T> List<T> queryList(String sql, Object params, Integer current, Integer pageSize, Class<T> taget) {
        if (current != null && pageSize != null) {
            sql = sql + " LIMIT " + (current - 1) * pageSize + "," + pageSize;

        }
        return queryList(sql, params, taget);
    }

    public static <T> List<T> queryList(String sql, Object params, Class<T> taget) {
        CommonSqlMapper commonSqlMapper = SpringUtil.getBean(CommonSqlMapper.class);
        List<Map<String, Object>> list = commonSqlMapper.query(tranParam(sql, params));
        return BeanUtils.mapsToBeans(list, taget);


    }

    public static <T> T queryOne(String sql, Object params, Class<T> taget) {
        List<T> list = queryList(sql, params, taget);
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    private static Map<String, Object> tranParam(String sql, Object params) {
        Map<String, Object> param = null;
        if (params != null && params instanceof Map) {
            param = (Map<String, Object>) params;
        } else {
            param = new HashMap<>();
            if (params != null) {
                param.putAll(BeanUtils.beanToMap(params));
            }
        }
        param.put("sql", sql);
        return param;
    }

    private static Map<String, Object> tranParam(String sqlId, String sql, Object params) {
        Map<String, Map<String, Object>> value = paramThreadLocal.get();
        if (value != null) {
            Map<String, Object> param = value.get(sqlId);
            if (param != null) {
                return param;
            }
        } else {
            value = new HashMap<>();
        }

        Map<String, Object> param = tranParam(sql, params);
        value.put(sqlId, param);
        paramThreadLocal.set(value);
        return param;
    }

    public static int queryCount(String sql, Object params) {
        sql = "select count(1) as count from (" + sql + ") t";
        CommonSqlMapper commonSqlMapper = SpringUtil.getBean(CommonSqlMapper.class);

        List<Map<String, Object>> list = commonSqlMapper.query(tranParam(sql, params));

        return ((Long) list.get(0).get("count")).intValue();

    }


    public static <T> IPage<T> queryPage(String sql, Object params, Integer current, Integer pageSize, Class<T> taget) {
        IPage<T> page = new Page<>();
        page.setCurrent(current);
        page.setSize(pageSize);
        page.setTotal(queryCount(sql, params));
        page.setRecords(queryList(sql, params, current, pageSize, taget));
        return page;
    }

    public static <T> IPage<T> queryPage(String sql, PageDTO params, Class<T> taget) {
        IPage<T> page = new Page<>();
        //当前查询的参数ID
        String sqlId = UUIDUtils.getUUID();
        page.setCurrent(params.getCurrent());
        page.setSize(params.getPageSize());
        page.setTotal(queryCount(sql, tranParam(sqlId, sql, params)));
        page.setRecords(queryList(sql, tranParam(sqlId, sql, params), params.getCurrent(), params.getPageSize(), taget));
        return page;
    }
}
