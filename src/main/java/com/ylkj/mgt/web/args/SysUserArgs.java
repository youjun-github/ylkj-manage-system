package com.ylkj.mgt.web.args;

import com.ylkj.mgt.entity.SysUser;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

/**
 * 用户请求参数
 * @author youjun
 * @create 2019-06-16 21:54
 */
public class SysUserArgs extends PageArgs {

    @ApiModelProperty(value = "登录名,唯一")
    private String loginName;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    public static SysUser convertEntity(SysUserArgs sysUserArgs) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserArgs, sysUser);
        return sysUser;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
