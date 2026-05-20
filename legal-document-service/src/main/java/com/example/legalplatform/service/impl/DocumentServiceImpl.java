package com.example.legalplatform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.legalplatform.entity.Document;
import com.example.legalplatform.mapper.DocumentMapper;
import com.example.legalplatform.service.DocumentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DocumentServiceImpl extends ServiceImpl<DocumentMapper, Document> implements DocumentService {

    @Override
    public List<Document> listByCaseId(Long caseId) {
        return lambdaQuery()
                .eq(Document::getCaseId, caseId)
                .orderByDesc(Document::getCreateTime)
                .list();
    }

    @Override
    public Document getDetail(Long id) {
        return getById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addDocument(Document document) {
        document.setCreateTime(LocalDateTime.now());
        document.setUpdateTime(LocalDateTime.now());
        save(document);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDocument(Document document) {
        document.setUpdateTime(LocalDateTime.now());
        updateById(document);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDocument(Long id) {
        removeById(id);
    }
}