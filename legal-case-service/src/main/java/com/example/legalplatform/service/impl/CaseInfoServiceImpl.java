package com.example.legalplatform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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

    // 新增：根据 用户ID + 角色 过滤案件（权限核心）
    @Override
    public List<CaseInfo> listByUserIdAndRole(Long userId, String role) {
        LambdaQueryWrapper<CaseInfo> wrapper = new LambdaQueryWrapper<>();

        // 管理员：不添加过滤条件，查询全部
        if (!"ADMIN".equals(role)) {
            // 非管理员：只看自己的案件
            // userId = null（游客）→ 返回空列表
            if (userId != null) {
                wrapper.eq(CaseInfo::getUserId, userId);
            } else {
                return List.of();
            }
        }

        return list(wrapper);
    }

    @Override
    public CaseInfo getCaseDetail(Long id) {
        return getById(id);
    }

    @Override
    public void addCase(CaseInfo caseInfo) {
        caseInfo.setCreateTime(new Date());
        caseInfo.setUpdateTime(new Date());
        save(caseInfo);
    }

    @Override
    public void updateCase(CaseInfo caseInfo) {
        updateById(caseInfo);
    }

    @Override
    public void deleteCase(Long id) {
        removeById(id);
    }
}