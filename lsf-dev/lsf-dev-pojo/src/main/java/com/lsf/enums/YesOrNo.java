package com.lsf.enums;

/**
 * 是否 枚举
 */
public enum YesOrNo {
    NO(0, "否"),
    YES(1, "是");

    public final int type;
    public final String value;

    YesOrNo(int type, String value){
        this.type = type;
        this.value = value;
    }
}
