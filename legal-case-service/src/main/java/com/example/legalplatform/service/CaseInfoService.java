package com.example.legalplatform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.legalplatform.entity.CaseInfo;
import java.util.List;

public interface CaseInfoService extends IService<CaseInfo> {

    // 旧方法（保留，不影响原有代码）
    List<CaseInfo> listByUserId(Long userId);

    // 新增：根据 用户ID + 角色 过滤案件列表（核心权限）
    List<CaseInfo> listByUserIdAndRole(Long userId, String role);

    CaseInfo getCaseDetail(Long id);
    void addCase(CaseInfo caseInfo);
    void updateCase(CaseInfo caseInfo);
    void deleteCase(Long id);
}