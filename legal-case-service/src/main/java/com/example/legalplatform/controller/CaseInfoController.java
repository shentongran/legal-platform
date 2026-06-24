package com.example.legalplatform.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.legalplatform.common.PageResult;
import com.example.legalplatform.common.Result;
import com.example.legalplatform.entity.CaseInfo;
import com.example.legalplatform.service.CaseInfoService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/case")
public class CaseInfoController {

    @Resource
    private CaseInfoService caseInfoService;

    @GetMapping("/page")
    public Result<PageResult<CaseInfo>> page(
            @RequestParam(defaultValue = "1") Long current,
            @RequestParam(defaultValue = "10") Long size,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String keyword) {

        IPage<CaseInfo> page = caseInfoService.pageList(current, size, userId, role, status, keyword);
        return Result.success(PageResult.of(page));
    }

    @GetMapping("/list")
    public Result<List<CaseInfo>> list(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String role) {
        return Result.success(caseInfoService.listByUserIdAndRole(userId, role));
    }

    @GetMapping("/{id}")
    public Result<CaseInfo> detail(@PathVariable Long id) {
        return Result.success(caseInfoService.getCaseDetail(id));
    }

    @PostMapping("/add")
    public Result<Void> add(@RequestBody CaseInfo caseInfo) {
        if (caseInfo.getCaseName() == null || caseInfo.getCaseName().trim().isEmpty()) {
            return Result.error("案件名称不能为空");
        }
        caseInfoService.addCase(caseInfo);
        return Result.success();
    }

    @PutMapping("/update")
    public Result<Void> update(@RequestBody CaseInfo caseInfo) {
        if (caseInfo.getId() == null) {
            return Result.error("案件ID不能为空");
        }
        caseInfoService.updateCase(caseInfo);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        caseInfoService.deleteCase(id);
        return Result.success();
    }
}
