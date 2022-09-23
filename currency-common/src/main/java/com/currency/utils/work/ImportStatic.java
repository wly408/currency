package com.currency.utils.work;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author wuliangyong
 * @Date 2022/3/14 10:53
 */
public class ImportStatic {

    public static Map<String, String> typeMap = new HashMap<>();

    public static final String tenantId = "bb85dd10e322495dafcda4e3028e5e68";

    static {
        typeMap.put("通用设备", "4f78fe27-7e7a-4760-815f-78b0a7bf7c04");
        typeMap.put("家具用具", "c220718e-fab3-42a9-92f3-5cf5c9f0a011");
        typeMap.put("专用设备", "ca040e14-b048-4df3-b5f2-36d0698582db");
        typeMap.put("文物及陈列品", "cc6658df-9cfb-4a4a-a7e5-5858458578be");
        typeMap.put("图书", "3f71d4bd-f89d-4792-b8f1-29801514adb5");

    }

    public static Map<String,TypeDetail> getTypeDetailList() {
        File file = new File("E:\\ly\\currency\\currency-common\\src\\main\\java\\com\\currency\\utils\\work\\资产信息定义.txt");
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        List<TypeDetail> list = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
//                sbf.append("'").append(tempStr.trim()).append("',");
                String[] array = tempStr.split(",");
                String name = array[0];
                String _id = array[1];
                String id = array[2];
                String catalogId = array[3];
                String unit = array[4];
                TypeDetail typeDetail = new TypeDetail(name,_id,id,catalogId,unit);
                list.add(typeDetail);

            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        Map<String,TypeDetail> map = new HashMap<>();
        for (TypeDetail typeDetail : list) {
            if(map.containsKey(typeDetail.getName())){
                throw new RuntimeException("根据名称获取资产错误");
            }
            map.put(typeDetail.getName(),typeDetail);
        }

        return map;
    }
    public static List<ImportData> getImportDataList() {
        File file = new File("E:\\ly\\currency\\currency-common\\src\\main\\java\\com\\currency\\utils\\work\\固定资产-资产信息导入数据.txt");
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        List<ImportData> list = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            int row = 1;
            while ((tempStr = reader.readLine()) != null) {
                String[] array = tempStr.split("\\s+");
                if(array.length!=3){
                    throw new RuntimeException("第"+row+"行数据错误");
                }
                String name = array[0];
                String serialNumber = array[1];
                String unit = array[2];
                ImportData importData = new ImportData(name,serialNumber,unit);
                list.add(importData);
                row++;

            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return list;

    }
    public static TypeDetail getTypeDetailByName(String name){

        TypeDetail typeDetail = getTypeDetailByName(name);
        if(typeDetail==null){
            throw new RuntimeException("找不到名称："+name+"对应的资产信息");

        }
        return typeDetail;

    }

}
