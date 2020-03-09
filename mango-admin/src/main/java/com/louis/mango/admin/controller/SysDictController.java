package com.louis.mango.admin.controller;

import com.louis.mango.admin.model.SysDict;
import com.louis.mango.admin.service.SysDictService;
import com.louis.mango.core.http.HttpResult;
import com.louis.mango.core.page.PageRequest;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: journey
 * @Date: 2020/2/19
 * @Time: 4:35 下午
 * @Description: 字典Controller
 */
@RestController
@RequestMapping("dict")
@Api(description = "字典管理")
public class SysDictController {
    @Autowired
    private SysDictService sysDictService;

    @PostMapping(value = "/save")
    public HttpResult save(@RequestBody SysDict record){
        return HttpResult.ok(sysDictService.save(record));
    }

    @DeleteMapping(value = "/delete")
    public HttpResult delete(@RequestBody List<SysDict> records){
        return HttpResult.ok(sysDictService.delete(records));
    }

    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest){
        return HttpResult.ok(sysDictService.findPage(pageRequest));
    }

    @GetMapping(value = "/findByLabel")
    public HttpResult findByLabel(@RequestParam String label){
        return HttpResult.ok(sysDictService.findByLabel(label));
    }
}
