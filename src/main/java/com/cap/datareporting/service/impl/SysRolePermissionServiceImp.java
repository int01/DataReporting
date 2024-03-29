package com.cap.datareporting.service.impl;

import com.cap.datareporting.dao.SysRolePermissionMapper;
import com.cap.datareporting.entity.SysRolePermission;
import com.cap.datareporting.entity.SysRolePermissionExample;
import com.cap.datareporting.service.SysRolePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: blog01
 * @description:
 * @author: MinHw or mz
 * @create: 2022-03-12 21:22
 **/
@Service
public class SysRolePermissionServiceImp implements SysRolePermissionService {

    @Resource
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public List<SysRolePermission> findRolePermissionByRid(Long roleId) {
        SysRolePermissionExample example = new SysRolePermissionExample();
        SysRolePermissionExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        return sysRolePermissionMapper.selectByExample(example);
    }
}
