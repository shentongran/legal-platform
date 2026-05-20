package com.example.legaltrialservice.service.impl;

import com.example.legaltrialservice.entity.Trial;
import com.example.legaltrialservice.mapper.TrialMapper;
import com.example.legaltrialservice.service.TrialService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TrialServiceImpl implements TrialService {

    private final TrialMapper trialMapper;

    public TrialServiceImpl(TrialMapper trialMapper) {
        this.trialMapper = trialMapper;
    }

    @Override
    public List<Trial> listAll() {
        return trialMapper.selectListWithCaseName();
    }

    @Override
    public List<Trial> listByCaseId(Long caseId) {
        return trialMapper.selectByCaseId(caseId);
    }

    @Override
    public Trial getById(Long id) {
        return trialMapper.selectById(id);
    }

    @Override
    public void add(Trial trial) {
        trialMapper.insert(trial);
    }

    @Override
    public void update(Trial trial) {
        trialMapper.updateById(trial);
    }

    @Override
    public void delete(Long id) {
        trialMapper.deleteById(id);
    }
}