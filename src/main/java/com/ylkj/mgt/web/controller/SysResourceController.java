package com.ylkj.mgt.web.controller;

import com.github.pagehelper.Page;
import com.ylkj.mgt.core.common.BaseController;
import com.ylkj.mgt.core.lang.Result;
import com.ylkj.mgt.entity.SysResource;
import com.ylkj.mgt.service.SysResourceService;
import com.ylkj.mgt.web.args.SysUserArgs;
import com.ylkj.mgt.web.mode.SysResourceMode;
import com.ylkj.mgt.web.mode.SysUserMode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author youjun
 * @create 2019-06-22 15:27
 */
@Api(tags = "资源接口")
@RestController
@RequestMapping(value = "/resource")
public class SysResourceController extends BaseController {

    @Autowired
    private SysResourceService sysResourceService;

    /**
     * 查询菜单列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "查询所有菜单")
    public Result<List<SysResourceMode>> findAll() {
        return ok(sysResourceService.findAll());
    }
}
