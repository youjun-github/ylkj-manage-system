package com.ylkj.mgt.service.impl;

import com.ylkj.mgt.core.common.impl.BaseServiceImpl;
import com.ylkj.mgt.entity.SysResource;
import com.ylkj.mgt.service.SysResourceService;
import com.ylkj.mgt.utils.CollectionUtil;
import com.ylkj.mgt.web.mode.SysResourceMode;
import com.ylkj.mgt.web.mode.SysUserMode;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author youjun
 * @create 2019-06-22 15:26
 */
@Service
public class SysResourceServiceImpl extends BaseServiceImpl<SysResource> implements SysResourceService {


    @Override
    public List<SysResourceMode> findAll() {
        return CollectionUtil.convert(selectAll(), SysResourceMode::entity);
    }
}
