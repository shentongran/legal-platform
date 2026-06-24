package com.example.legalplatform.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.legalplatform.entity.CaseInfo;
import java.util.List;

public interface CaseInfoService extends IService<CaseInfo> {

    List<CaseInfo> listByUserId(Long userId);

    List<CaseInfo> listByUserIdAndRole(Long userId, String role);

    IPage<CaseInfo> pageList(Long current, Long size, Long userId, String role, String status, String keyword);

    CaseInfo getCaseDetail(Long id);

    void addCase(CaseInfo caseInfo);

    void updateCase(CaseInfo caseInfo);

    void deleteCase(Long id);
}
