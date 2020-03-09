package com.louis.mango.admin.service.impl;

import com.louis.mango.admin.dao.SysUserRoleMapper;
import com.louis.mango.admin.model.SysMenu;
import com.louis.mango.admin.model.SysUser;
import com.louis.mango.admin.model.SysUserRole;
import com.louis.mango.admin.service.SysMenuService;
import com.louis.mango.admin.service.SysUserService;
import com.louis.mango.admin.dao.SysUserMapper;
import com.louis.mango.core.page.MybatisPageHelper;
import com.louis.mango.core.page.PageRequest;
import com.louis.mango.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: journey
 * @Date: 2020/2/17
 * @Time: 3:37 下午
 * @Description:
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.findAll();
    }

    @Override
    public int save(SysUser record) {
        // 1. 查询当前数据库是否存在此用户，如果存在则修改，否则就创建。
        Long id = null;
        if(record.getId() == null || record.getId() == 0){
            //新增用户
            sysUserMapper.insertSelective(record);
            id = record.getId();
        }else{
            //更新用户信息
            sysUserMapper.updateByPrimaryKeySelective(record);
        }

        // 更新用户角色
        if(id != null && id == 0){
            return 1;
        }

        if(id != null) {
            for(SysUserRole sysUserRole:record.getUserRoles()) {
                sysUserRole.setUserId(id);
            }
        } else {
            sysUserRoleMapper.deleteByUserId(record.getId());
        }
        for(SysUserRole sysUserRole:record.getUserRoles()) {
            sysUserRoleMapper.insertSelective(sysUserRole);
        }
        return 1;
    }

    @Override
    public int delete(SysUser record) {
        return sysUserMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysUser> records) {
        for (SysUser record:records){
            delete(record);
        }
        return 1;
    }

    @Override
    public SysUser findById(Long id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest){
        return MybatisPageHelper.findPage(pageRequest, sysUserMapper);
    }

    @Override
    public SysUser findByName(String name) {
        return sysUserMapper.findByName(name);
    }

    @Override
    public Set<String> findPermissions(String userName) {
        Set<String> perms = new HashSet<>();
        List<SysMenu> sysMenus = sysMenuService.findByUser(userName);
        for(SysMenu sysMenu:sysMenus){
            if(sysMenu.getPerms() != null && !"".equals(sysMenu.getPerms())){
                perms.add(sysMenu.getPerms());
            }
        }
        return perms;
    }

    @Override
    public List<SysUserRole> findUserRoles(Long userId) {
        return sysUserRoleMapper.findUserRoles(userId);
    }
}
