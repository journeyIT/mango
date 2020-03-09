package com.louis.mango.admin.service.impl;

import com.louis.mango.admin.constant.SysConstants;
import com.louis.mango.admin.dao.SysMenuMapper;
import com.louis.mango.admin.dao.SysRoleMapper;
import com.louis.mango.admin.dao.SysRoleMenuMapper;
import com.louis.mango.admin.model.SysMenu;
import com.louis.mango.admin.model.SysRole;
import com.louis.mango.admin.model.SysRoleMenu;
import com.louis.mango.admin.service.SysRoleService;
import com.louis.mango.core.page.MybatisPageHelper;
import com.louis.mango.core.page.PageRequest;
import com.louis.mango.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: journey
 * @Date: 2020/2/20
 * @Time: 9:55 上午
 * @Description: 角色管理Service接口实现类
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public int save(SysRole record) {
        // 查询数据库，若当前存在则修改，否则新增
        if(record.getId() == null || record.getId() == 0){
            return sysRoleMapper.insertSelective(record);
        }
        return sysRoleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(SysRole record) {
        return sysRoleMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysRole> records) {
        for(SysRole record:records){
            delete(record);
        }
        return 1;
    }

    @Override
    public SysRole findById(Long id) {
        return null;
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Object label = pageRequest.getParam("name");
        if(label != null){
            return MybatisPageHelper.findPage(pageRequest, sysRoleMapper, "findPageByName" , label);
        }
        return MybatisPageHelper.findPage(pageRequest,sysRoleMapper);
    }


    @Override
    public List<SysRole> findAll() {
        return sysRoleMapper.findAll();
    }

    @Override
    public List<SysMenu> findRoleMenus(Long roleId) {
        // 通过roleId查询role相关信息
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(roleId);
        if(SysConstants.ADMIN.equalsIgnoreCase(sysRole.getName())) {
            // 如果是超级管理员，返回全部
            return sysMenuMapper.findAll();
        }
        // 通过roleId查询menu数据
        return sysMenuMapper.findRoleMenus(roleId);
    }

    @Transactional
    @Override
    public int saveRoleMenus(List<SysRoleMenu> records) {
        if(records == null || records.isEmpty()) {
            return 1;
        }
        Long roleId = records.get(0).getRoleId();
        sysRoleMenuMapper.deleteByRoleId(roleId);
        for(SysRoleMenu record:records) {
            sysRoleMenuMapper.insertSelective(record);
        }
        return 1;
    }
}
