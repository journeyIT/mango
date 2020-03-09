package com.louis.mango.admin.service;

import com.louis.mango.admin.model.SysMenu;
import com.louis.mango.admin.model.SysRole;
import com.louis.mango.admin.model.SysRoleMenu;
import com.louis.mango.core.service.CurdService;

import java.util.List;

/**
 * @Author: journey
 * @Date: 2020/2/20
 * @Time: 9:54 上午
 * @Description:
 */
public interface SysRoleService extends CurdService<SysRole> {
    List<SysRole> findAll();

    List<SysMenu> findRoleMenus(Long roleId);

    /**
     * 保存角色菜单
     * @param records
     * @return
     */
    int saveRoleMenus(List<SysRoleMenu> records);
}
