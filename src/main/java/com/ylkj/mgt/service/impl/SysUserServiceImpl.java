package com.ylkj.mgt.service.impl;

import com.github.pagehelper.Page;
import com.ylkj.mgt.core.common.impl.BaseServiceImpl;
import com.ylkj.mgt.core.lang.Assert;
import com.ylkj.mgt.core.lang.HttpCode;
import com.ylkj.mgt.core.lang.Result;
import com.ylkj.mgt.entity.SysUser;
import com.ylkj.mgt.service.SysUserService;
import com.ylkj.mgt.utils.CollectionUtil;
import com.ylkj.mgt.utils.CommonUtils;
import com.ylkj.mgt.utils.MD5Utils;
import com.ylkj.mgt.web.args.LoginArgs;
import com.ylkj.mgt.web.args.SysUserArgs;
import com.ylkj.mgt.web.mode.SysUserMode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.stream.Collectors;

/**
 * @author youjun
 * @create 2019-06-16 21:40
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {

    @Override
    public Page<SysUserMode> selectByPage(SysUserArgs userArgs) {
        Assert.isTrue(userArgs!=null, HttpCode.PARAM_INVALID, "用户参数不能为空");
        Page<SysUser> userPage = select(SysUserArgs.convertEntity(userArgs), userArgs.getPageNum(), userArgs.getPageSize());
        return CollectionUtil.convert(userPage, SysUserMode::entity);
    }

    @Override
    public Result<SysUserMode> login(LoginArgs loginArgs) {
        Assert.isTrue(!StringUtils.isEmpty(loginArgs.getLoginName()), HttpCode.PARAM_INVALID, "用户名不能为空");
        Assert.isTrue(!StringUtils.isEmpty(loginArgs.getPassword()), HttpCode.PARAM_INVALID, "密码不能为空");
        SysUser entity = new SysUser();
        entity.setLoginName(loginArgs.getLoginName());
        entity.setPassword(MD5Utils.getMD5Code(loginArgs.getPassword()));
        SysUser user = selectOne(entity);
        Assert.isTrue(user!=null, HttpCode.NO_DATA, "用户名或密码错误");
        return Result.ok(SysUserMode.entity(user));
    }
}
