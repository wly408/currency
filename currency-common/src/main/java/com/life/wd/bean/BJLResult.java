package com.life.wd.bean;

import com.life.wd.enums.BJLResultEnum;
import lombok.Data;

import java.util.List;

@Data
public class BJLResult extends BaseBean{

    private BJLResultEnum result;

    private List<PaiItem> xianList;

    private List<PaiItem> zhuangList;
    private int xianNum;
    private int zhuangNum;
    private int count;



}
