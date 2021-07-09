package com.currency.enums;

public enum CommonEnum {
    SYS_USER_TYPE_ADMIN("sysUserType","1","管理员"),
    SYS_USER_ID_ADMIN("sysUserId","1","超级管理员ID"),
    SYS_USER_ID_UN_USER("sysUserId","-1","系统"),


    SYS_ROLE_GUEST("sysRole","GUEST","游客");
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
