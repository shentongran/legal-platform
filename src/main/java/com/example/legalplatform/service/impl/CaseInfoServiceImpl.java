package com.example.legalplatform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.legalplatform.entity.CaseInfo;
import com.example.legalplatform.mapper.CaseInfoMapper;
import com.example.legalplatform.service.CaseInfoService;
import org.springframework.stereotype.Service;

@Service
public class CaseInfoServiceImpl extends ServiceImpl<CaseInfoMapper, CaseInfo> implements CaseInfoService {
}