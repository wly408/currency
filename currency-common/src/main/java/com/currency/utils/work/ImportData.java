package com.currency.utils.work;

import lombok.Data;

import java.util.Map;

/**
 * @Author wuliangyong
 * @Date 2022/3/14 12:54
 */
@Data
public class ImportData {

    private String name;

    private String serialNumber;

    private String unit;

    private TypeDetail typeDetail;

    public static Map<String,TypeDetail> typeDetailMap;

    public ImportData(String name, String serialNumber, String unit) {
        this.name = name;
        this.serialNumber = serialNumber;
        this.unit = unit;
    }
    public TypeDetail getTypeDetail(){
        if(typeDetailMap==null){
            typeDetailMap = ImportStatic.getTypeDetailList();

        }
        TypeDetail typeDetail = typeDetailMap.get(this.name);
        if(typeDetail==null){
//            System.out.println("找不到名称：["+name+"]对应的资产信息");
            throw new RuntimeException("找不到名称：["+name+"]对应的资产信息");

        }
        return typeDetail;
    }

}
