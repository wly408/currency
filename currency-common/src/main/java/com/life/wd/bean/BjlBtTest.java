package com.life.wd.bean;

import com.currency.exception.BusinessException;
import com.life.wd.enums.BJLResultEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wuliangyong
 * @Date 2022/2/9 12:49
 */
public class BjlBtTest {
    final public static int[] baseBt = new int[]{5, 6, 15, 33, 70, 150, 330, 680};

    public static void main(String[] args) {
        int[] lan = getLan(baseBt,1.2);
        double count = 0;

        for (int i : lan) {
            System.out.print(i+",");
            count+=i;
        }
        System.out.println();
        System.out.println(count-1341);

    }
    public static List<int[]> createLanList(int lanCount,double bs){
        List<int[]> array = new ArrayList<>();
        array.add(baseBt);
        for (int i = 1; i < lanCount; i++) {
            int[] datas = getLan(array.get(i-1),bs);
            array.add(datas);
        }
        return array;
    }



    public static int[] getLan(int[] baseBt, double bs) {
        int[] lan = new int[baseBt.length];
        for (int i = 0; i < lan.length; i++) {
            lan[i] = (int) (baseBt[i] * bs);
        }
        return lan;
    }
    public static double getLanMoney(int[] baseBt) {
        double money = 0;
        for (int i = 0; i < baseBt.length; i++) {
            money+=baseBt[i];
        }
        return money;
    }


    public static void test() {
        double total = 10000;
        //胜进缆
        List<int[]> sjLan = createLanList(100,1.2);
        //当前胜进缆的位置
        int currentLanIndex = 0;
        //翻倍后胜进？
        double sjXs = 1;

        final int testXueCount = 70 * 10;


        List<BJLResult> rsList = BJLTest.getTestResult(testXueCount);
        //当前缆的盈利金额
        double currentLanWinMoney = 0;
        int btIndex = 0;


        for (int i = 1; i < rsList.size() - 1; i++) {
            //获取当前缆
            int[] currentLan = sjLan.get(currentLanIndex);
            double lanMoney = getLanMoney(currentLan);
            BJLResult before = rsList.get(i - 1);
            BJLResult now = rsList.get(i);
            BJLResult next = rsList.get(i + 1);
            int xzje = currentLan[btIndex];
            if(xzje>total){
                throw new BusinessException("亏光了");
            }

            if (!before.getResult().getCode().equals(now.getResult().getCode())) {
                total+=xzje;
                currentLanWinMoney+=xzje;
                btIndex = 0;
                //需要进缆
                if(currentLanWinMoney>=lanMoney*sjXs){
                    currentLanWinMoney = 0;
                    currentLanIndex = currentLanIndex>=sjLan.size()?0:currentLanIndex+1;
                }

            } else {
                total-=xzje;
                currentLanWinMoney-=xzje;
                btIndex = btIndex>=currentLan.length?0:btIndex+1;
                //是否需要进缆


            }

        }

//

    }


}

