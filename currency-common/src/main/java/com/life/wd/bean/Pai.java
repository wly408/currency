package com.life.wd.bean;

import lombok.Data;

import java.util.List;

@Data
public class Pai extends BaseBean {

    private List<PaiItem> itemList;
}
