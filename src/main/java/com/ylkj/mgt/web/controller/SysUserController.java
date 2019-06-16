package com.ylkj.mgt.web.controller;

import com.github.pagehelper.Page;
import com.ylkj.mgt.core.common.BaseController;
import com.ylkj.mgt.core.lang.Result;
import com.ylkj.mgt.service.SysUserService;
import com.ylkj.mgt.web.args.SysUserArgs;
import com.ylkj.mgt.web.mode.SysUserMode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
