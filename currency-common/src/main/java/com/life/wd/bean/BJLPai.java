package com.life.wd.bean;

import com.life.wd.util.BJLUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class BJLPai extends BaseBean {

    private int paiCount;

    private List<PaiItem> paiItemList;

    public BJLPai() {
        this(8);
    }

    public BJLPai(int paiCount) {
        this.paiCount = paiCount;
        paiItemList = new ArrayList<>();

        for (int i = 0; i < paiCount; i++) {
            Pai pai = new Pai(true);
            paiItemList.addAll(pai.getItemList());
        }
    }

    public void xp() {
        Collections.shuffle(paiItemList);
    }

    public List<BJLResult> test() {
        this.xp();
        return BJLUtil.fp(this);
    }
}
