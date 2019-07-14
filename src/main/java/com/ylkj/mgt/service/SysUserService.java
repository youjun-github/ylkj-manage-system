package com.ylkj.mgt.service;

import com.github.pagehelper.Page;
import com.ylkj.mgt.core.common.BaseService;
import com.ylkj.mgt.core.lang.Result;
import com.ylkj.mgt.entity.SysUser;
import com.ylkj.mgt.web.args.LoginArgs;
import com.ylkj.mgt.web.args.RegisterArgs;
import com.ylkj.mgt.web.args.SysUserArgs;
import com.ylkj.mgt.web.mode.SysUserMode;

/**
 * @author youjun
 * @create 2019-06-16 21:40
 */
public interface SysUserService extends BaseService<SysUser> {

    /**
     * 分页查询用户
     * @param userArgs
     * @return
     */
    Page<SysUserMode> selectByPage(SysUserArgs userArgs);

    /**
     * 登录
     * @param loginArgs
     * @return
     */
    Result<SysUserMode> login(LoginArgs loginArgs);

    /**
     * 注册
     * @param registerArgs
     */
    void register(RegisterArgs registerArgs);

    /**
     * 获取注册验证码
     * @param mobile
     * @return
     */
    String registerVerCode(String mobile);

}
