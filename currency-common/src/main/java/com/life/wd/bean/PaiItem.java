package com.life.wd.bean;

import com.life.wd.enums.PaiItemDecorEnum;
import lombok.Data;

@Data
public class PaiItem extends BaseBean{
    private int realNum;
    private int num;
    private boolean isTen;
    private PaiItemDecorEnum decor;

    public PaiItem() {
    }

    public PaiItem(int num, boolean isTen, PaiItemDecorEnum decor) {
        this.num = num;
        this.isTen = isTen;
        this.decor = decor;
    }
}
