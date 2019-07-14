package com.ylkj.mgt.service.impl;

import com.github.pagehelper.Page;
import com.ylkj.mgt.core.common.impl.BaseServiceImpl;
import com.ylkj.mgt.core.lang.Assert;
import com.ylkj.mgt.core.lang.HttpCode;
import com.ylkj.mgt.core.lang.MessageException;
import com.ylkj.mgt.core.lang.Result;
import com.ylkj.mgt.entity.SysUser;
import com.ylkj.mgt.model.MobileVerificationCode;
import com.ylkj.mgt.service.SysUserService;
import com.ylkj.mgt.utils.CollectionUtil;
import com.ylkj.mgt.utils.CommonUtils;
import com.ylkj.mgt.utils.MD5Utils;
import com.ylkj.mgt.utils.VerificationCodeUtils;
import com.ylkj.mgt.web.args.LoginArgs;
import com.ylkj.mgt.web.args.RegisterArgs;
import com.ylkj.mgt.web.args.SysUserArgs;
import com.ylkj.mgt.web.mode.SysUserMode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
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

    @Override
    public void register(RegisterArgs registerArgs) {
        //格式验证
        Assert.isTrue(!StringUtils.isEmpty(registerArgs.getMobile()), HttpCode.DATA_NULL, "手机号不能为空");
        Assert.isTrue(!StringUtils.isEmpty(registerArgs.getEmail()), HttpCode.DATA_NULL, "邮箱不能为空");
        Assert.isTrue(!StringUtils.isEmpty(registerArgs.getPassword()), HttpCode.DATA_NULL, "密码不能为空");
        Assert.isTrue(!StringUtils.isEmpty(registerArgs.getConfirmPwd()), HttpCode.DATA_NULL, "请确认密码");
        Assert.isTrue(!StringUtils.isEmpty(registerArgs.getVerificationCode()), HttpCode.DATA_NULL, "验证码不能为空");
        if (!registerArgs.getPassword().equals(registerArgs.getConfirmPwd())) {
            throw new MessageException(HttpCode.PARAM_INVALID, "两次输入的密码不符");
        }
        Assert.isTrue(CommonUtils.isMail(registerArgs.getEmail()), HttpCode.PARAM_INVALID, "请填写正确邮箱");
        Assert.isTrue(CommonUtils.isCellPhoneNumber(registerArgs.getMobile()), HttpCode.PARAM_INVALID, "手机号不正确");
        //验证码验证
        MobileVerificationCode mobileVerificationCode = VerificationCodeUtils.verCodeMap.get(registerArgs.getMobile());
        if (!registerArgs.getVerificationCode().equals(mobileVerificationCode.getVerCode())) {
            throw new MessageException(HttpCode.PARAM_INVALID, "验证码不正确");
        }
        long st = System.currentTimeMillis()/1000;
        if (st - mobileVerificationCode.getTimestamp() > VerificationCodeUtils.VER_CODE_VALIDITY) {
            throw new MessageException(HttpCode.PARAM_INVALID, "验证码已过有效期，请重新获取");
        }
        //验证完成，生成用户信息
        SysUser registerUser = new SysUser();
        registerUser.setLoginName(registerArgs.getEmail());
        registerUser.setPassword(registerArgs.getPassword());
        registerUser.setCreateTime(new Date());
        registerUser.setUpdateTime(new Date());
        registerUser.setAdmin(false);
        registerUser.setNickName("");
        registerUser.setEmail(registerArgs.getEmail());
        registerUser.setMobilePhone(registerArgs.getMobile());
        registerUser.setStatus(1);
        registerUser.setRemarks("注册用户");
        insertSelective(registerUser);
    }

    @Override
    public String registerVerCode(String mobile) {
        Assert.isTrue(!StringUtils.isEmpty(mobile), HttpCode.DATA_NULL, "手机号不能为空");
        Assert.isTrue(CommonUtils.isCellPhoneNumber(mobile), HttpCode.PARAM_INVALID, "手机号不正确");
        long st = System.currentTimeMillis()/1000;
        MobileVerificationCode mobileVerificationCode = VerificationCodeUtils.verCodeMap.get(mobile);
        if (mobileVerificationCode != null) {
            long duration = st - mobileVerificationCode.getTimestamp();
            if (duration < VerificationCodeUtils.VER_CODE_VALIDITY) {
                throw new MessageException(HttpCode.NO_AUTH, "三分钟内只允许获取一次验证码");
            }
        } else {
            mobileVerificationCode = new MobileVerificationCode();
            mobileVerificationCode.setMobile(mobile);
        }
        String verCode = (int) ((Math.random() * 9 + 1) * 100000) + "";
        logger.info(String.format("手机号-验证码：%s-%s", mobile, verCode));
        mobileVerificationCode.setVerCode(verCode);
        mobileVerificationCode.setTimestamp(st);
        VerificationCodeUtils.verCodeMap.put(mobile, mobileVerificationCode);
        return verCode;
    }

}
