package com.louis.mango.admin.service;

import com.louis.mango.admin.model.SysUser;
import com.louis.mango.admin.model.SysUserRole;
import com.louis.mango.core.page.PageRequest;
import com.louis.mango.core.page.PageResult;
import com.louis.mango.core.service.CurdService;

import java.util.List;
import java.util.Set;

public interface SysUserService extends CurdService<SysUser> {
    /**
     * 查找所有用户
     * @return
     */
    List<SysUser> findAll();

    /**
     * 分页查询用户
     * @Param pageRequest
     * @return
     */
    PageResult findPage(PageRequest pageRequest);

    /**
     * 通过userName查询用户
     * @param name
     * @return
     */
    SysUser findByName(String name);

    /**
     * 通过userName查询用户权限
     * @param name
     * @return
     */
    Set<String> findPermissions(String name);

    List<SysUserRole> findUserRoles(Long userId);
}
