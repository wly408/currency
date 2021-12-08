package com.life.wd.enums;

public enum LHResultEnum {

    HE("0", "和"),
    LONG("1", "龙"),
    HU("2", "虎");


    private String code;
    private String value;
    private String name;

    LHResultEnum(String code, String name) {
        this.code = code;
        this.value = code;
        this.name = name;
    }

    LHResultEnum(String code, String value, String name) {
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
