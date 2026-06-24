package com.example.legalplatform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.legalplatform.entity.CaseInfo;
import com.example.legalplatform.mapper.CaseInfoMapper;
import com.example.legalplatform.service.CaseInfoService;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class CaseInfoServiceImpl extends ServiceImpl<CaseInfoMapper, CaseInfo> implements CaseInfoService {

    @Override
    public List<CaseInfo> listByUserId(Long userId) {
        LambdaQueryWrapper<CaseInfo> wrapper = new LambdaQueryWrapper<>();
        if (userId != null) {
            wrapper.eq(CaseInfo::getUserId, userId);
        }
        return list(wrapper);
    }

    @Override
    public List<CaseInfo> listByUserIdAndRole(Long userId, String role) {
        LambdaQueryWrapper<CaseInfo> wrapper = new LambdaQueryWrapper<>();
        if (!"ADMIN".equals(role)) {
            if (userId != null) {
                wrapper.eq(CaseInfo::getUserId, userId);
            } else {
                return List.of();
            }
        }
        wrapper.orderByDesc(CaseInfo::getCreateTime);
        return list(wrapper);
    }

    @Override
    public IPage<CaseInfo> pageList(Long current, Long size, Long userId, String role, String status, String keyword) {
        LambdaQueryWrapper<CaseInfo> wrapper = new LambdaQueryWrapper<>();

        if (!"ADMIN".equals(role)) {
            if (userId != null) {
                wrapper.eq(CaseInfo::getUserId, userId);
            } else {
                return new Page<>(current, size);
            }
        }

        if (status != null && !status.isEmpty()) {
            wrapper.eq(CaseInfo::getStatus, status);
        }

        if (keyword != null && !keyword.isEmpty()) {
            wrapper.and(w -> w.like(CaseInfo::getCaseName, keyword)
                    .or().like(CaseInfo::getCaseNo, keyword)
                    .or().like(CaseInfo::getPartyName, keyword));
        }

        wrapper.orderByDesc(CaseInfo::getCreateTime);
        return page(new Page<>(current, size), wrapper);
    }

    @Override
    public CaseInfo getCaseDetail(Long id) {
        return getById(id);
    }

    @Override
    public void addCase(CaseInfo caseInfo) {
        caseInfo.setCreateTime(new Date());
        caseInfo.setUpdateTime(new Date());
        if (caseInfo.getStatus() == null || caseInfo.getStatus().isEmpty()) {
            caseInfo.setStatus("PENDING");
        }
        save(caseInfo);
    }

    @Override
    public void updateCase(CaseInfo caseInfo) {
        caseInfo.setUpdateTime(new Date());
        updateById(caseInfo);
    }

    @Override
    public void deleteCase(Long id) {
        removeById(id);
    }
}
