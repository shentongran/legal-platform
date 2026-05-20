package com.example.legaltrialservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.legaltrialservice.entity.Trial;
import java.util.List;

public interface TrialService extends IService<Trial> {
    List<Trial> listByCaseId(Long caseId);
    Trial getDetail(Long id);
    void addTrial(Trial trial);
    void updateTrial(Trial trial);
    void deleteTrial(Long id);
}