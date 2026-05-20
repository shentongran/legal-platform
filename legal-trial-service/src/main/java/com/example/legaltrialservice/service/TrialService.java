package com.example.legaltrialservice.service;

import com.example.legaltrialservice.entity.Trial;
import java.util.List;

public interface TrialService {
    List<Trial> listAll();
    List<Trial> listByCaseId(Long caseId);
    Trial getById(Long id);
    void add(Trial trial);
    void update(Trial trial);
    void delete(Long id);
}