package com.life.wd.enums;

public enum BJLResultEnum {

    HE("0", "和"),
    XIAN("1", "闲"),
    ZHUANG("2", "庄");


    private String code;
    private String value;
    private String name;

    BJLResultEnum(String code, String name) {
        this.code = code;
        this.value = code;
        this.name = name;
    }

    BJLResultEnum(String code, String value, String name) {
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
