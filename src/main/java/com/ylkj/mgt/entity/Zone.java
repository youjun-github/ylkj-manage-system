package com.ylkj.mgt.entity;

import javax.persistence.*;

@Table(name = "zone")
public class Zone {
    /**
     * 标识
     */
    private Integer code;

    /**
     * 父级行政区划,第一级为:0
     */
    @Column(name = "parent_code")
    private Integer parentCode;

    /**
     * 级别
     */
    private Integer level;

    /**
     * 名称
     */
    private String name;

    /**
     * 简称
     */
    private String shortname;

    /**
     * 获取标识
     *
     * @return code - 标识
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 设置标识
     *
     * @param code 标识
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 获取父级行政区划,第一级为:0
     *
     * @return parent_code - 父级行政区划,第一级为:0
     */
    public Integer getParentCode() {
        return parentCode;
    }

    /**
     * 设置父级行政区划,第一级为:0
     *
     * @param parentCode 父级行政区划,第一级为:0
     */
    public void setParentCode(Integer parentCode) {
        this.parentCode = parentCode;
    }

    /**
     * 获取级别
     *
     * @return level - 级别
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置级别
     *
     * @param level 级别
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取简称
     *
     * @return shortname - 简称
     */
    public String getShortname() {
        return shortname;
    }

    /**
     * 设置简称
     *
     * @param shortname 简称
     */
    public void setShortname(String shortname) {
        this.shortname = shortname == null ? null : shortname.trim();
    }
}