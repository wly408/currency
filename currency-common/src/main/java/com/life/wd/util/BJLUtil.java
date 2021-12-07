package com.life.wd.util;

import com.life.wd.bean.BJLPai;
import com.life.wd.bean.BJLResult;
import com.life.wd.bean.PaiItem;
import com.life.wd.enums.BJLResultEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class BJLUtil {


    public static List<BJLResult> fp(BJLPai bjlPai) {
        List<PaiItem> paiItemList = bjlPai.getPaiItemList();
        List<BJLResult> resultList = new ArrayList<>();
        int itemLength = paiItemList.size();
        for (int i = 0; i < itemLength; ) {
            int last = itemLength - i;
            if (last < 6) {
                break;
            }
            PaiItem xianOne = paiItemList.get(i++);
            PaiItem zhuangOne = paiItemList.get(i++);
            PaiItem xianTwo = paiItemList.get(i++);
            PaiItem zhuangTwo = paiItemList.get(i++);
            BJLResult bjlResult = getResultFirst(xianOne, xianTwo, zhuangOne, zhuangTwo);
            if (bjlResult.getResult() == null) {
                int xianNum = bjlResult.getXianNum();
                int zhuangNum = bjlResult.getZhuangNum();
                List<PaiItem> xianList = bjlResult.getXianList();
                List<PaiItem> zhuangList = bjlResult.getZhuangList();

                if (xianNum >= 6) {
                    PaiItem zhuangThree = paiItemList.get(i++);
                    zhuangList.add(zhuangThree);
                } else if (zhuangNum >= 7) {
                    PaiItem xianThree = paiItemList.get(i++);
                    xianList.add(xianThree);
                }else if(zhuangNum==3){
                    PaiItem xianThree = paiItemList.get(i++);
                    xianList.add(xianThree);
                    if(xianThree.getNum()!=8){
                        PaiItem zhuangThree = paiItemList.get(i++);
                        zhuangList.add(zhuangThree);
                    }
                }else if(zhuangNum==6){
                    PaiItem xianThree = paiItemList.get(i++);
                    xianList.add(xianThree);
                    if(xianThree.getNum()==6||xianThree.getNum()==7){
                        PaiItem zhuangThree = paiItemList.get(i++);
                        zhuangList.add(zhuangThree);
                    }
                }else if(zhuangNum==4){
                    PaiItem xianThree = paiItemList.get(i++);
                    xianList.add(xianThree);
                    if(!(xianThree.getNum()==1||xianThree.getNum()==10||xianThree.getNum()==8||xianThree.getNum()==9)){
                        PaiItem zhuangThree = paiItemList.get(i++);
                        zhuangList.add(zhuangThree);
                    }
                }else if(zhuangNum==5){
                    PaiItem xianThree = paiItemList.get(i++);
                    xianList.add(xianThree);
                    if(!(xianThree.getNum()==1||xianThree.getNum()==2||xianThree.getNum()==3||xianThree.getNum()==10||xianThree.getNum()==8||xianThree.getNum()==9)){
                        PaiItem zhuangThree = paiItemList.get(i++);
                        zhuangList.add(zhuangThree);
                    }
                }else {
                    PaiItem xianThree = paiItemList.get(i++);
                    xianList.add(xianThree);
                    PaiItem zhuangThree = paiItemList.get(i++);
                    zhuangList.add(zhuangThree);
                }
                bjlResult = getResultByList(xianList,zhuangList);
            }
            if(!BJLResultEnum.HE.getCode().equals(bjlResult.getResult().getCode())){
                resultList.add(bjlResult);
            }


        }
        return resultList;

    }

    private static BJLResult getResultFirst(PaiItem xianOne, PaiItem xianTwo, PaiItem zhuangOne, PaiItem zhuangTwo) {
        BJLResult result = new BJLResult();
        int xianNum = (xianOne.getNum() + xianTwo.getNum()) % 10;
        int zhuangNum = (zhuangOne.getNum() + zhuangTwo.getNum()) % 10;
        result.setXianList(new ArrayList<>(Arrays.asList(xianOne, xianTwo)));
        result.setZhuangList(new ArrayList<>(Arrays.asList(zhuangOne, zhuangTwo)));
        result.setZhuangNum(zhuangNum);
        result.setXianNum(xianNum);
        if (xianNum >= 6 && zhuangNum >= 6) {
            BJLResultEnum resultEnum = BJLResultEnum.HE;
            if (xianNum > zhuangNum) {
                resultEnum = BJLResultEnum.XIAN;
            } else if (zhuangNum > xianNum) {
                resultEnum = BJLResultEnum.ZHUANG;
            }
            result.setResult(resultEnum);
        }
        return result;
    }
    private static BJLResult getResultByList(List<PaiItem> xianList,List<PaiItem> zhuangList) {
        BJLResult result = new BJLResult();
        int xianNum = -1;
        for (PaiItem item : xianList) {
            xianNum+=item.getNum();
        }
        xianNum=xianNum%10;
        int zhuangNum = -1;
        for (PaiItem item : zhuangList) {
            zhuangNum+=item.getNum();
        }
        zhuangNum=zhuangNum%10;

        result.setXianList(xianList);
        result.setZhuangList(zhuangList);
        result.setZhuangNum(zhuangNum);
        result.setXianNum(xianNum);
        BJLResultEnum resultEnum = BJLResultEnum.HE;
        if (xianNum > zhuangNum) {
            resultEnum = BJLResultEnum.XIAN;
        } else if (zhuangNum > xianNum) {
            resultEnum = BJLResultEnum.ZHUANG;
        }
        result.setResult(resultEnum);
        return result;
    }
    public static void initCount( List<BJLResult> rsList){

        int count = 1;
        BJLResult before = null;
        for (BJLResult bjlResult : rsList) {

            if(before==null){
                bjlResult.setCount(1);
            }else{
                if(before.getResult().getCode().equals(bjlResult.getResult().getCode())){
                    bjlResult.setCount(++count);
                }else{
                    bjlResult.setCount(1);
                    count=1;
                }
            }
            before = bjlResult;


        }
    }


}
