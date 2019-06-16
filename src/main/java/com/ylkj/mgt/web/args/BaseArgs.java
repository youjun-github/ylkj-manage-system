package com.ylkj.mgt.web.args;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 基础请求参数
 * @author youjun
 * @create 2019-06-16 21:55
 */
@ApiModel(value = "基础请求参数")
public class BaseArgs {

    @ApiModelProperty(value = "ID")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
