package com.example.legalplatform.controller;

import com.example.legalplatform.entity.CaseInfo;
import com.example.legalplatform.service.CaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/case")
public class CaseInfoController {

    @Autowired
    private CaseInfoService caseInfoService;

    // 1. 查询所有案件（所有人可访问）
    @GetMapping("/list")
    public List<CaseInfo> getCaseList() {
        return caseInfoService.list();
    }

    // 2. 新增案件（仅admin可访问）
    @PostMapping("/add")
    public boolean addCase(@RequestBody CaseInfo caseInfo) {
        // 默认状态为待审理
        if (caseInfo.getStatus() == null) {
            caseInfo.setStatus("pending");
        }
        return caseInfoService.save(caseInfo);
    }

    // 3. 按ID更新案件状态（核心接口：仅admin/judge可访问）
    @PostMapping("/updateStatus/{id}")
    public boolean updateCaseStatus(
            @PathVariable Long id,  // 案件ID
            @RequestParam String status  // 新状态：pending/processing/finished
    ) {
        // 1. 校验状态合法性
        if (!"pending".equals(status) && !"processing".equals(status) && !"finished".equals(status)) {
            throw new RuntimeException("状态不合法：仅支持 pending(待审理)/processing(审理中)/finished(已结案)");
        }

        // 2. 查询案件是否存在
        CaseInfo caseInfo = caseInfoService.getById(id);
        if (caseInfo == null) {
            throw new RuntimeException("案件不存在：ID=" + id);
        }

        // 3. 更新状态
        caseInfo.setStatus(status);
        return caseInfoService.updateById(caseInfo);
    }

    // 4. 扩展：按案号查询单个案件（方便前端展示）
    @GetMapping("/detail/{caseNo}")
    public CaseInfo getCaseByNo(@PathVariable String caseNo) {
        return caseInfoService.lambdaQuery().eq(CaseInfo::getCaseNo, caseNo).one();
    }
}