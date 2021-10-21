package com.currency.sys.mapper;

import java.util.List;
import java.util.Map;

public interface CommonSqlMapper {

    List<Map<String, Object>> query(Map<String, Object> param);

    int delete(Map<String, Object> param);

    int update(Map<String, Object> param);

    int insert(Map<String, Object> param);

}
