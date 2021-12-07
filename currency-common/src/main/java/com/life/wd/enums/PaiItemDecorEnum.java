package com.life.wd.enums;

public enum PaiItemDecorEnum {

    SPADE("spade", "黑桃"),
    HEART("heart", "红桃"),
    BLOSSOM("blossom", "梅花"),
    BLOCK("block", "方块");


    private String code;
    private String value;
    private String name;

    PaiItemDecorEnum(String code, String name) {
        this.code = code;
        this.value = code;
        this.name = name;
    }

    PaiItemDecorEnum(String code, String value, String name) {
        this.code = code;
        this.value = value;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
