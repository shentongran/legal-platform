package com.example.legalplatform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.legalplatform.entity.Trial;
import java.util.List;

public interface TrialService extends IService<Trial> {
    List<Trial> listAll();
    List<Trial> listByCaseId(Long caseId);
    Trial getTrialById(Long id);
    void addTrial(Trial trial);
    void updateTrial(Trial trial);
    void deleteTrial(Long id);
}
