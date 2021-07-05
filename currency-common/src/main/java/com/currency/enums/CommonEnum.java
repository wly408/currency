package com.currency.enums;

public enum CommonEnum {
    SYS_USER_TYPE_ADMIN("sysUserType","1","管理员")

;
    private String group;
    private String value;
    private String name;
    private String desc;

    CommonEnum(String group,String value, String name) {
        this.group = group;
        this.value = value;
        this.name = name;
    }
    CommonEnum(String group,String value, String name, String desc) {
        this.group = group;
        this.value = value;
        this.name = name;
        this.desc = desc;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
