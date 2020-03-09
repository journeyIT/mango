package com.louis.mango.admin.service;

import com.louis.mango.admin.model.SysDept;
import com.louis.mango.core.service.CurdService;

import java.util.List;

/**
 * @Author: journey
 * @Date: 2020/2/19
 * @Time: 8:38 下午
 * @Description:
 */
public interface SysDeptService extends CurdService<SysDept> {

    List<SysDept> findTree();
}
