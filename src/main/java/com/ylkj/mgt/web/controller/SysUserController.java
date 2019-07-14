package com.ylkj.mgt.web.controller;

import com.github.pagehelper.Page;
import com.ylkj.mgt.constant.Constant;
import com.ylkj.mgt.core.common.BaseController;
import com.ylkj.mgt.core.lang.Result;
import com.ylkj.mgt.service.SysUserService;
import com.ylkj.mgt.utils.VerificationCodeUtils;
import com.ylkj.mgt.web.args.LoginArgs;
import com.ylkj.mgt.web.args.RegisterArgs;
import com.ylkj.mgt.web.args.SysUserArgs;
import com.ylkj.mgt.web.mode.SysUserMode;
import com.ylkj.mgt.web.mode.VerCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

/**
 * @author youjun
 * @create 2019-06-16 21:44
 */
@Api(tags="用户接口")
@RestController
@RequestMapping(value = "/user")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 分页查询用户
     * @param userArgs 查询条件
     * @return 用户列表
     */
    @PostMapping("/page")
    @ApiOperation(value = "分页查询用户")
    public Result<Page<SysUserMode>> findByPage(@RequestBody SysUserArgs userArgs) {
        return ok(sysUserService.selectByPage(userArgs));
    }

    /**
     * 登录
     * @param loginArgs
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "登录参数")
    public Result<SysUserMode> login(@RequestBody LoginArgs loginArgs) {
        return sysUserService.login(loginArgs);
    }

    /**
     * 验证码
     * @return
     */
    @GetMapping("/vercode")
    @ApiOperation(value = "验证码")
    public Result<VerCode> verCode() {
        String code = RandomStringUtils.random(Constant.COUNT, Constant.VER_CODE);
        VerCode verCode = new VerCode();
        verCode.setCode(code);
        byte[] bytes = VerificationCodeUtils.drawImage(code);
        verCode.setImg(Base64Utils.encodeToString(bytes));
        return Result.ok(verCode);
    }

    @PostMapping("/registerVerCode")
    @ApiOperation(value = "获取注册验证码")
    public Result<String> registerVerCode(@RequestParam String mobile) {
        return ok(sysUserService.registerVerCode(mobile));
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册")
    public Result<?> register(@RequestBody RegisterArgs registerArgs) {
        sysUserService.register(registerArgs);
        return Result.ok(null);
    }

    /**
     * 添加用户
     *
     * @param userArgs 用户信息
     * @return 用户主键
     */
    /*@PostMapping("insert")
    @ApiOperation(value = "新增用户", notes = "type 默认为 1 系统，loginName、password、必填")
    public Result saveUser(@RequestBody SysUserArgs userArgs) {
        return sysUserService.add(userArgs);
    }

    *//**
     * 删除用户
     * @param userId userId
     * @return 处理结果
     *//*
    @GetMapping("delete")
    @ApiOperation(value = "删除用户", notes = "根据用户 ID 删用户")
    public Result deleteUser(Long userId) {
        return sysUserService.deleteUser(userId);
    }

    *//**
     * 修改用户
     *
     * @param userArgs 用户信息
     * @return 用户
     *//*
    @PostMapping("update")
    @ApiOperation(value = "修改用户", notes = "用户 ID = 61258573660635136 必传")
    public Result update(@RequestBody SysUserArgs userArgs) {
        return sysUserService.update(userArgs);
    }*/

}
