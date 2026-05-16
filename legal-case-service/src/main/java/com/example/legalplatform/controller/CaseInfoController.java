package com.example.legalplatform.controller;

import com.example.legalplatform.entity.Result;
import com.example.legalplatform.entity.CaseInfo;
import com.example.legalplatform.service.CaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/case")
public class CaseInfoController {

    @Autowired
    private CaseInfoService caseInfoService;

    // 列表
    @GetMapping("/list")
    public Result<List<CaseInfo>> list(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String role) {

        return Result.success(caseInfoService.listByUserIdAndRole(userId, role));
    }

    // 详情
    @GetMapping("/{id}")
    public Result<CaseInfo> detail(@PathVariable Long id) {
        return Result.success(caseInfoService.getCaseDetail(id));
    }

    // 新增案件
    @PostMapping("/add")
    public Result<String> add(@RequestBody CaseInfo caseInfo) {
        // 简单校验
        if (caseInfo.getCaseName() == null || caseInfo.getCaseName().trim().isEmpty()) {
            return Result.error("案件名称不能为空");
        }
        caseInfoService.addCase(caseInfo);
        return Result.success("新增成功");
    }

    // 修改
    @PutMapping("/update")
    public Result<Void> update(@RequestBody CaseInfo caseInfo) {
        caseInfoService.updateCase(caseInfo);
        return Result.success();
    }

    // 删除
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        caseInfoService.deleteCase(id);
        return Result.success();
    }
}