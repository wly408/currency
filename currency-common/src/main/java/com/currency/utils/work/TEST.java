package com.currency.utils.work;

import java.util.List;

/**
 * @Author wuliangyong
 * @Date 2022/3/14 11:24
 */
public class TEST {

    public static void main(String[] args) {
        List<ImportData> importDataList =  ImportStatic.getImportDataList();
        System.out.println("共导入:"+importDataList.size());
        for (int i = 0; i < importDataList.size(); i++) {
//            System.out.println("开始生成,第"+i+"条数据SQL:");
            printSql(importDataList.get(i));

        }

    }
    static int start = 2;

    public static void printSql(ImportData importData){
//        if(importData.getTypeDetail()==null){
//            return;
//        }

        StringBuffer orderIdSql = new StringBuffer();
        int currentNo = start++;
        orderIdSql.append("INSERT INTO `generate_order_id` (`tenant_id`,`type`,`generate_date`,`order_id`) VALUES(");

        orderIdSql.append("'"+ImportStatic.tenantId+"',");
        orderIdSql.append("'zcrk','20220314','"+currentNo+"'");
        orderIdSql.append(");");

        String s=String.format("%03d", currentNo);
        String orderNo = "zcrk20220314"+s;
        System.out.println(orderNo);


        StringBuffer sql = new StringBuffer();
        sql.append("INSERT INTO `assets_item` ( `tenant_id`,`order_no`,`assets_id`,`serial_number`,`storage_date`,`status`,");
        sql.append("`assets_status`,`used`,`amount`,`supplier_id`,`images`,`remark`,`scrap`,`create_time`,`create_user`,`update_time`,`update_user` ");

        sql.append(") VALUES");

        sql.append("(");

        sql.append("'"+ImportStatic.tenantId+"',");
        sql.append("'"+orderNo+"',");
        //资产标识,assets表的id
        sql.append("'"+importData.getTypeDetail().getId()+"',");
        sql.append("'"+importData.getSerialNumber()+"',");
        sql.append("'2022-03-14 00:00:00',");
        sql.append("'1',");
        sql.append("'1',");
        sql.append("0,");
        sql.append("'1.00',");


        sql.append("NULL,");
        sql.append("'[]',");
        sql.append("NULL,");
        sql.append("0,");
        sql.append("'2022-03-14 11:33:38',");
        sql.append("'205116183850',");
        sql.append("'2022-03-14 11:33:38',");
        sql.append("'205116183850'");
        sql.append(");");

//        System.out.println(orderIdSql);

//        System.out.println(sql);


    }
}
