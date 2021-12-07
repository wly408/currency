package com.life.wd.bean;

import com.life.wd.enums.PaiItemDecorEnum;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Pai extends BaseBean {

    private List<PaiItem> itemList;

    private boolean isTen;

    public Pai() {
        this(false);
    }

    public Pai(boolean isTen) {
        this.isTen = isTen;
        itemList = new ArrayList<>();
        PaiItemDecorEnum[] list =  PaiItemDecorEnum.values();
        for (int i = 0; i < 13; i++) {
            for (PaiItemDecorEnum currencyEnum : list) {
                PaiItem item = new PaiItem(i+1,isTen,currencyEnum);
                itemList.add(item);
            }
        }

    }
}
