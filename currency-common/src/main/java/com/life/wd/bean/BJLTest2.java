package com.life.wd.bean;

import com.life.wd.enums.BJLResultEnum;
import com.life.wd.util.BJLUtil;

import java.util.ArrayList;
import java.util.List;

public class BJLTest2 {

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
        int[] xzjes = new int[]{200,400,800};
//        int[] xzjes = new int[]{5,10,20};
        List<BJLResult> rsList = getTestResult(1);
        String[] test = new String[]{BJLResultEnum.ZHUANG.getCode(),BJLResultEnum.ZHUANG.getCode(),BJLResultEnum.XIAN.getCode()};
        int suc = 0;
//        int error = 0;
        double xzzje = 0;
        int testIndex = 0;
        for (int i = 0; i < rsList.size()-1; i++) {

            BJLResult now = rsList.get(i);
            if(now.getCount()<=3){
                continue;
            }

            if(now.getResult().getCode().equals(test[testIndex])){
                double yinli = xzjes[testIndex];
                if(now.getResult().getCode().equals(BJLResultEnum.ZHUANG.getCode())){
                    yinli = xzjes[testIndex]*0.95;
                }
                total=total+yinli;

                testIndex=0;
                xzzje=xzzje+xzjes[testIndex];
                System.out.println(",盈利:"+yinli+",total:"+total);

            }else {
                total=total-xzjes[testIndex];
                xzzje=xzzje+xzjes[testIndex];
                System.out.println(",亏:"+xzjes[testIndex]+",total:"+total);

                testIndex++;
                if(testIndex>=test.length){
                    testIndex=0;
                }
            }

//            if(!before.getResult().getCode().equals(now.getResult().getCode())){
//                double yinli = xzjes[suc];
//                if(now.getResult().getCode().equals(BJLResultEnum.ZHUANG.getCode())){
//                    yinli = xzjes[suc]*0.95;
//                }
//                total=total+yinli;
//
//
//                xzzje=xzzje+xzjes[suc];
//                suc++;
//                if(suc>=xzjes.length){
//                    suc=0;
//                }
//                System.out.println("前:"+before.getResult().getCode()+",盈利:"+yinli+",total:"+total);
//            }else{
//
//                total=total-xzjes[suc];
//                xzzje=xzzje+xzjes[suc];
//                System.out.println("前:"+before.getResult().getCode()+",亏:"+xzjes[suc]+",total:"+total);
//                suc=0;
//
//
//            }

        }
        System.out.println(total);
        System.out.println(total+xzzje/10000*60);

    }

}
