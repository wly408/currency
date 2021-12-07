package com.life.wd.bean;

import com.life.wd.enums.BJLResultEnum;
import com.life.wd.util.BJLUtil;

import java.util.ArrayList;
import java.util.List;

public class BJLTest {

    public static List<BJLResult> getTestResult(int xue){
        List<BJLResult> rsList = new ArrayList<>();
        int max = 0;
        for (int i = 0; i <xue ; i++) {
            BJLPai pai = new BJLPai(8);
            List<BJLResult> list = pai.test();
            BJLUtil.initCount(list);

            for (BJLResult bjlResult : list) {
                if(bjlResult.getCount()>max){
                    max=bjlResult.getCount();
                }
            }
            rsList.addAll(list);

        }
        System.out.println(max);
        return rsList;
    }

    public static void main(String[] args) {
        double total = 10000D;
//        int[] xzjes = new int[]{5,6,15,33,70,150,330,680};
        int[] xzjes = new int[]{5,10,20};
        List<BJLResult> rsList = getTestResult(10000);
        int suc = 0;
        int error = 0;
        double xzzje = 0;
        for (int i = 2; i < rsList.size()-1; i++) {
            BJLResult before = rsList.get(i-1);
            BJLResult now = rsList.get(i);
            BJLResult next = rsList.get(i+1);

//            if(!before.getResult().getCode().equals(now.getResult().getCode())){
//                double yinli = xzjes[error];
//                if(now.getResult().getCode().equals(BJLResultEnum.ZHUANG.getCode())){
//                    yinli = xzjes[error]*0.95;
//                }
//                total=total+yinli;
//
//                error=0;
//                xzzje=xzzje+xzjes[error];
//                System.out.println("前:"+before.getResult().getCode()+",盈利:"+yinli+",total:"+total);
//            }else{
//
//                total=total-xzjes[error];
//                xzzje=xzzje+xzjes[error];
//                System.out.println("前:"+before.getResult().getCode()+",亏:"+xzjes[error]+",total:"+total);
//
//                error++;
//                if(error>=xzjes.length){
//                    error=0;
//                }
//
//            }
            if(!before.getResult().getCode().equals(now.getResult().getCode())){
                double yinli = xzjes[suc];
                if(now.getResult().getCode().equals(BJLResultEnum.ZHUANG.getCode())){
                    yinli = xzjes[suc]*0.95;
                }
                total=total+yinli;


                xzzje=xzzje+xzjes[suc];
                suc++;
                if(suc>=xzjes.length){
                    suc=0;
                }
                System.out.println("前:"+before.getResult().getCode()+",盈利:"+yinli+",total:"+total);
            }else{

                total=total-xzjes[suc];
                xzzje=xzzje+xzjes[suc];
                System.out.println("前:"+before.getResult().getCode()+",亏:"+xzjes[suc]+",total:"+total);
                suc=0;


            }

        }
        System.out.println(total);
        System.out.println(total+xzzje/10000*60);

    }

}
