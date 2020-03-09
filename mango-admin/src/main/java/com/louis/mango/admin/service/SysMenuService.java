package com.louis.mango.admin.service;

import com.louis.mango.admin.model.SysMenu;
import com.louis.mango.admin.model.SysUser;
import com.louis.mango.core.service.CurdService;

import java.util.List;

/**
 * @Author: journey
 * @Date: 2020/2/19
 * @Time: 7:00 下午
 * @Description:
 */
public interface SysMenuService  extends CurdService<SysMenu> {
    /**
     * 通过用户name查询Menu
     * @param userName
     * @return
     */
    List<SysMenu> findByUser(String userName);
}
