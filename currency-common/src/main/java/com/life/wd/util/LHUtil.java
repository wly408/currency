package com.life.wd.util;

import com.life.wd.bean.LHPai;
import com.life.wd.bean.LHResult;
import com.life.wd.bean.PaiItem;
import com.life.wd.enums.LHResultEnum;

import java.util.ArrayList;
import java.util.List;

public class LHUtil {

    public static List<LHResult> fp(LHPai lhPai) {
        List<PaiItem> paiItemList = lhPai.getPaiItemList();
        List<LHResult> resultList = new ArrayList<>();
        int itemLength = paiItemList.size();
        for (int i = 0; i < itemLength; ) {
            int last = itemLength - i;
            if (last < 2) {
                break;
            }
            LHResult result = new LHResult();
            LHResultEnum resultEnum = LHResultEnum.HE;
            PaiItem lo = paiItemList.get(i++);
            PaiItem hu = paiItemList.get(i++);
            if (lo.getNum() > hu.getNum()) {
                resultEnum = LHResultEnum.LONG;
            } else if (lo.getNum() < hu.getNum()) {
                resultEnum = LHResultEnum.HU;
            }
            result.setResult(resultEnum);
            result.setHu(hu);
            result.setLo(lo);
            resultList.add(result);

        }
        return resultList;
    }

    public static void initCount(List<LHResult> rsList) {

        int count = 1;
        LHResult before = null;
        for (LHResult bjlResult : rsList) {

            if (before == null) {
                bjlResult.setCount(1);
            } else {
                if (!bjlResult.getResult().getCode().equals(LHResultEnum.HE.getCode())) {
                    if (before.getResult().getCode().equals(bjlResult.getResult().getCode())) {
                        bjlResult.setCount(++count);
                    } else {
                        bjlResult.setCount(1);
                        count = 1;
                    }
                }
            }
            before = bjlResult;
        }
    }
}
