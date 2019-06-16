package com.ylkj.mgt.enums;

/**
 * @author youjun
 * @create 2019-04-26 18:07
 */
public enum JavaTypeEnum {

    /**
     * String类型
     */
    STRING("java.lang.String", "String");

    private String fullName;

    private String shortName;

    private JavaTypeEnum(String fullName, String shortName) {
        this.fullName = fullName;
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        shortName = shortName;
    }
}
