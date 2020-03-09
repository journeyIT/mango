package com.louis.mango.admin.dao;

import com.louis.mango.admin.model.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
     * 查询全部
     *
     * @return
     */
    List<SysUser> findAll();

    /**
     * 分页查询
     *
     * @return
     */
    List<SysUser> findPage();

    SysUser findByName(@Param(value = "name") String name);
}
