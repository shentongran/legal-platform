package com.example.legalplatform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.legalplatform.entity.Trial;
import com.example.legalplatform.mapper.TrialMapper;
import com.example.legalplatform.service.TrialService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TrialServiceImpl extends ServiceImpl<TrialMapper, Trial> implements TrialService {

    @Override
    public List<Trial> listAll() {
        return list();
    }

    @Override
    public List<Trial> listByCaseId(Long caseId) {
        LambdaQueryWrapper<Trial> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Trial::getCaseId, caseId)
               .orderByDesc(Trial::getTrialTime);
        return list(wrapper);
    }

    @Override
    public Trial getTrialById(Long id) {
        return getById(id);
    }

    @Override
    public void addTrial(Trial trial) {
        trial.setCreateTime(LocalDateTime.now());
        trial.setUpdateTime(LocalDateTime.now());
        save(trial);
    }

    @Override
    public void updateTrial(Trial trial) {
        trial.setUpdateTime(LocalDateTime.now());
        updateById(trial);
    }

    @Override
    public void deleteTrial(Long id) {
        removeById(id);
    }
}
