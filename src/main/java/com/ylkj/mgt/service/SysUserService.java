package com.ylkj.mgt.service;

import com.github.pagehelper.Page;
import com.ylkj.mgt.core.common.BaseService;
import com.ylkj.mgt.entity.SysUser;
import com.ylkj.mgt.web.args.SysUserArgs;
import com.ylkj.mgt.web.mode.SysUserMode;

/**
 * @author youjun
 * @create 2019-06-16 21:40
 */
public interface SysUserService extends BaseService<SysUser> {

    /**
     * 分頁查詢用戶
     * @param userArgs
     * @return
     */
    Page<SysUserMode> selectByPage(SysUserArgs userArgs);

}
