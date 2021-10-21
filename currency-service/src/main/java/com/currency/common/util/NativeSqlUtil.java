package com.currency.common.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.currency.sys.mapper.CommonSqlMapper;
import com.currency.utils.SpringUtil;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class NativeSqlUtil {
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
        Map<String, Object> param = new HashMap<>();
        param.put("sql", sql);
        if (params != null) {
            if (params instanceof Map) {
                param.putAll((Map) params);
            } else {
                param.putAll(BeanUtils.beanToMap(params));

            }
        }
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
}
