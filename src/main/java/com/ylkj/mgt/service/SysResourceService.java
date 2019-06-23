package com.ylkj.mgt.service;

import com.ylkj.mgt.core.common.BaseService;
import com.ylkj.mgt.entity.SysResource;
import com.ylkj.mgt.web.mode.SysResourceMode;

import java.util.List;

/**
 * @author youjun
 * @create 2019-06-22 15:25
 */
public interface SysResourceService extends BaseService<SysResource> {

    List<SysResourceMode> findAll();

}
