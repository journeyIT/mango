package com.louis.mango.admin.controller;

import com.louis.mango.admin.constant.SysConstants;
import com.louis.mango.admin.dao.SysRoleMapper;
import com.louis.mango.admin.model.SysRole;
import com.louis.mango.admin.model.SysRoleMenu;
import com.louis.mango.admin.service.SysRoleService;
import com.louis.mango.core.http.HttpResult;
import com.louis.mango.core.page.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: journey
 * @Date: 2020/2/20
 * @Time: 9:50 上午
 * @Description: 角色管理Controller
 */
@RestController
@RequestMapping("role")
@Api(description = "角色管理")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @PostMapping(value = "/save")
    @ApiOperation(value = "保存角色")
    public HttpResult save(@RequestBody SysRole record){
        return HttpResult.ok(sysRoleService.save(record));
    }

    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "删除角色")
    public HttpResult delete(@RequestBody List<SysRole> records){
        return HttpResult.ok(sysRoleService.delete(records));
    }

    @PostMapping(value = "/findPage")
    @ApiOperation(value = "分页查询")
    public HttpResult findPage(@RequestBody PageRequest pageRequest){
        return HttpResult.ok(sysRoleService.findPage(pageRequest));
    }

    @GetMapping(value = "/findAll")
    @ApiOperation(value = "查询所有")
    public HttpResult findAll(){
        return HttpResult.ok(sysRoleService.findAll());
    }

    @GetMapping(value = "/findRoleMenus")
    @ApiOperation(value = "查询角色菜单")
    public HttpResult findRoleMenus(@RequestParam Long roleId){
        return HttpResult.ok(sysRoleService.findRoleMenus(roleId));
    }

    @PostMapping(value = "/saveRoleMenus")
    @ApiOperation(value = "保存角色菜单")
    public HttpResult saveRoleMenus(@RequestBody List<SysRoleMenu> records){
        for(SysRoleMenu record:records) {
            SysRole sysRole = sysRoleMapper.selectByPrimaryKey(record.getRoleId());
            if(SysConstants.ADMIN.equalsIgnoreCase(sysRole.getName())) {
                // 如果是超级管理员，不允许修改
                return HttpResult.error("超级管理员拥有所有菜单权限，不允许修改！");
            }
        }
        return HttpResult.ok(sysRoleService.saveRoleMenus(records));
    }


}
