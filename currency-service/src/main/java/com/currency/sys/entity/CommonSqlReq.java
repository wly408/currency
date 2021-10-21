package com.currency.sys.entity;

import lombok.Data;

import java.util.HashMap;


public class CommonSqlReq extends HashMap {
    private String sql;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
