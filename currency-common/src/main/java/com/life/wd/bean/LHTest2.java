package com.life.wd.bean;

import com.life.wd.enums.LHResultEnum;
import com.life.wd.util.LHUtil;

import java.util.ArrayList;
import java.util.List;

public class LHTest2 {

    public static List<LHResult> getTestResult(int xue){
        List<LHResult> rsList = new ArrayList<>();
        int max = 0;
        for (int i = 0; i <xue ; i++) {
            LHPai pai = new LHPai(8);
            List<LHResult> list = pai.test();
            LHUtil.initCount(list);

            for (LHResult bjlResult : list) {
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
        int[] xzjes = new int[]{5,6,15,33,70,150,330};
        //1777110,2158640,2739170,3049940，3550460,4121350,4559600
        xzjes = new int[]{20};
        List<LHResult> rsList = getTestResult(10);

//        if(true){
//            int loCount = 0;
//            int huCount =0;
//            int heCount =0;
//            for (LHResult result : rsList) {
//                if(result.getResult().getCode().equals(LHResultEnum.HE.getCode())){
//                    heCount++;
//                }else if(result.getResult().getCode().equals(LHResultEnum.HU.getCode())){
//                    huCount++;
//                }else if(result.getResult().getCode().equals(LHResultEnum.LONG.getCode())){
//                    loCount++;
//                }
//            }
//            System.out.println(loCount);
//            System.out.println(huCount);
//            System.out.println(heCount);
//
//            return;
//        }
        int suc = 0;
        int error = 0;
        double xzzje = 0;
        int playCount = 0;
        for (int i = 2; i < rsList.size()-1; i++) {
            LHResult beforeB = rsList.get(i-2);
            LHResult before = rsList.get(i-1);
            LHResult now = rsList.get(i);
            playCount++;
            int index = i-1;
            boolean isCon =true;
            while(beforeB.getResult().getCode().equals(LHResultEnum.HE.getCode())){
                index--;
                if(index>=0){
                    beforeB=rsList.get(index);
                }else{
                    isCon =false;
                    break;
                }
            }
            if(!isCon){
                continue;
            }
            int count = before.getCount();
//            if(count<4){
//                continue;
//            }
            if(now.getResult().getCode().equals(LHResultEnum.HE.getCode())){
                total=total-xzjes[error]/2;
                xzzje=xzzje+xzjes[error];
                System.out.println("第"+playCount+"次,前:"+before.getResult().getCode()+"现："+now.getResult().getCode()+",和亏:"+xzjes[error]/2+",total:"+total);


            }else if(beforeB.getResult().getCode().equals(now.getResult().getCode())){
                double yinli = xzjes[error];

                total=total+yinli;

                error=0;
                xzzje=xzzje+xzjes[error];
                System.out.println("第"+playCount+"次,前:"+before.getResult().getCode()+"现："+now.getResult().getCode()+",盈利:"+yinli+",total:"+total);
            }else{
                total=total-xzjes[error];
                xzzje=xzzje+xzjes[error];
                System.out.println("第"+playCount+"次,前:"+before.getResult().getCode()+"现："+now.getResult().getCode()+",亏:"+xzjes[error]+",total:"+total);
                error++;
                if(error>=xzjes.length){
                    error=0;
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
        System.out.println(playCount);
        System.out.println(playCount/100/30);

    }

}
