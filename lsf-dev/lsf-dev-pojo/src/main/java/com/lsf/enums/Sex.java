package com.lsf.enums;

/**
 * 性别枚举
 */
public enum Sex {
    woman(0, "女"),
    man(1, "男"),
    secrecy(2, "保密");

    public final int sex;
    public final String desc;

    Sex(int sex, String desc){
        this.sex = sex;
        this.desc = desc;
    }
}
