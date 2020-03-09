package com.louis.mango.admin.controller;

import com.louis.mango.admin.constant.SysConstants;
import com.louis.mango.admin.model.SysUser;
import com.louis.mango.admin.service.SysUserService;
import com.louis.mango.admin.util.PasswordUtils;
import com.louis.mango.core.http.HttpResult;
import com.louis.mango.core.page.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: journey
 * @Date: 2020/2/17
 * @Time: 3:42 下午
 * @Description: 用户Controller
 */
@RestController
@RequestMapping("user")
@Api(description = "用户管理")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 保存用户或更新用户
     * @param record
     * @return
     */
    @PostMapping(value="/save")
    @ApiOperation(value = "保存或修改用户")
    public HttpResult save(@ApiParam(name = "record", value = "用户对象", required = true) @RequestBody SysUser record) {
        // 1. 通过id从数据库中查找相关用户，如果不为null则说明存在该用户同时修改用户信息，如果为null则说明不存在此用户那就新增。
        SysUser user = sysUserService.findById(record.getId());
        if(user != null) {
            if(SysConstants.ADMIN.equalsIgnoreCase(user.getName())) {
                return HttpResult.error("超级管理员不允许修改!");
            }
        }
        if(record.getPassword() != null) {
            String salt = PasswordUtils.getSalt();
            if(user == null) {
                // 新增用户
                if(sysUserService.findByName(record.getName()) != null) {
                    return HttpResult.error("用户名已存在!");
                }
                String password = PasswordUtils.encode(record.getPassword(), salt);
                record.setSalt(salt);
                record.setPassword(password);
            } else {
                // 修改用户, 且修改了密码
                if(!record.getPassword().equals(user.getPassword())) {
                    String password = PasswordUtils.encode(record.getPassword(), salt);
                    record.setSalt(salt);
                    record.setPassword(password);
                }
            }
        }
        return HttpResult.ok(sysUserService.save(record));
    }

    /**
     * 删除用户信息
     * @param records
     * @return
     */
    @DeleteMapping(value="/delete")
    public HttpResult delete(@RequestBody List<SysUser> records) {
        for(SysUser record:records) {
            SysUser sysUser = sysUserService.findById(record.getId());
            if(sysUser != null && SysConstants.ADMIN.equalsIgnoreCase(sysUser.getName())) {
                return HttpResult.error("超级管理员不允许删除!");
            }
        }
        return HttpResult.ok(sysUserService.delete(records));
    }


    /**
     * 分页查询用户信息
     * @param pageRequest
     * @return
     */
    @PostMapping(value = "/findPage")
    @ApiOperation(value = "分页查询")
    public HttpResult findPage(@ApiParam(name = "pageRequest", value = "分页查询条件", required = true) @RequestBody PageRequest pageRequest){
        return HttpResult.ok(sysUserService.findPage(pageRequest));
    }

    /**
     * 通过用户名查询用户信息
     * @param name
     * @return
     */
    @GetMapping(value="/findByName")
    @ApiOperation(value = "根据名称查询")
    public HttpResult findByName(@RequestParam String name) {
        return HttpResult.ok(sysUserService.findByName(name));
    }

    /**
     * 查询用户权限
     * @param name
     * @return
     */
    @GetMapping(value = "/findPermissions")
    @ApiOperation(value = "查询用户权限")
    public HttpResult findPermissions(@RequestParam String name){
        return HttpResult.ok(sysUserService.findPermissions(name));
    }

    /**
     * 通过用户id查询用户角色
     * @param userId
     * @return
     */
    @GetMapping(value = "/findUserRoles")
    @ApiOperation(value = "查询用户角色")
    public HttpResult findUserRoles(@RequestParam Long userId){
        return HttpResult.ok(sysUserService.findUserRoles(userId));
    }

    /**
     * 查询所有用户信息
     * @return
     */
    @GetMapping(value = "/findAll")
    @ApiOperation(value = "查询所有")
    public Object findAll(){
        return sysUserService.findAll();
    }


}
