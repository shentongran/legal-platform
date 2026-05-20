package com.example.legaltrialservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.legaltrialservice.entity.Trial;
import com.example.legaltrialservice.mapper.TrialMapper;
import com.example.legaltrialservice.service.TrialService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TrialServiceImpl extends ServiceImpl<TrialMapper, Trial> implements TrialService {

    @Override
    public List<Trial> listByCaseId(Long caseId) {
        return lambdaQuery()
                .eq(Trial::getCaseId, caseId)
                .orderByDesc(Trial::getTrialTime)
                .list();
    }

    @Override
    public Trial getDetail(Long id) {
        return getById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addTrial(Trial trial) {
        trial.setCreateTime(LocalDateTime.now());
        trial.setUpdateTime(LocalDateTime.now());
        save(trial);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTrial(Trial trial) {
        trial.setUpdateTime(LocalDateTime.now());
        updateById(trial);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTrial(Long id) {
        removeById(id);
    }
}