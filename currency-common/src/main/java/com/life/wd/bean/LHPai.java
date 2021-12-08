package com.life.wd.bean;

import com.life.wd.util.LHUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Data
public class LHPai {
    private int paiCount;

    private List<PaiItem> paiItemList;

    public LHPai() {
        this(8);
    }

    public LHPai(int paiCount) {
        this.paiCount = paiCount;
        paiItemList = new ArrayList<>();

        for (int i = 0; i < paiCount; i++) {
            Pai pai = new Pai(false);
            paiItemList.addAll(pai.getItemList());
        }
    }

    public void xp() {
        Collections.shuffle(paiItemList);
    }

    public List<LHResult> test() {
        this.xp();
        return LHUtil.fp(this);
    }
}
