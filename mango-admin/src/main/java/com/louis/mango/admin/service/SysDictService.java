package com.louis.mango.admin.service;

import com.louis.mango.admin.model.SysDict;
import com.louis.mango.core.service.CurdService;

import java.util.List;

/**
 * @Author: journey
 * @Date: 2020/2/19
 * @Time: 3:56 下午
 * @Description: 字典管理
 */
public interface SysDictService extends CurdService<SysDict> {
    /**
     * 根据名称查询
     * @param label
     * @return
     */
    List<SysDict> findByLabel(String label);
}
