package com.currency.utils.work;

import lombok.Data;

/**
 * @Author wuliangyong
 * @Date 2022/3/14 11:03
 */
@Data
public class TypeDetail {

    private String name;
    private String _id;
    private String id;
    private String catalogId;
    private String unit;

    public TypeDetail(String name, String _id, String id, String catalogId, String unit) {
        this.name = name;
        this._id = _id;
        this.id = id;
        this.catalogId = catalogId;
        this.unit = unit;
    }
}
