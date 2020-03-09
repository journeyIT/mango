package com.louis.mango.admin.controller;

import com.louis.mango.admin.model.SysDept;
import com.louis.mango.admin.service.SysDeptService;
import com.louis.mango.core.http.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: journey
 * @Date: 2020/2/19
 * @Time: 8:22 下午
 * @Description:
 */
@RestController
@RequestMapping("dept")
@Api(description = "机构管理")
public class SysDeptController {
    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 保存机构
     * @param record
     * @return
     */
    @PostMapping(value="/save")
    @ApiOperation(value = "保存机构")
    public HttpResult save(@RequestBody SysDept record) {
        return HttpResult.ok(sysDeptService.save(record));
    }

    /**
     * 删除机构
     * @param records
     * @return
     */
    @DeleteMapping(value="/delete")
    @ApiOperation(value = "删除机构")
    public HttpResult delete(@RequestBody List<SysDept> records) {
        return HttpResult.ok(sysDeptService.delete(records));
    }

    /**
     * 查询机构树
     * @return
     */
    @GetMapping(value="/findTree")
    public HttpResult findTree(){
        return HttpResult.ok(sysDeptService.findTree());
    }
}
