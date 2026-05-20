package com.example.legalplatform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.legalplatform.entity.Document;
import java.util.List;

public interface DocumentService extends IService<Document> {

    /**
     * 根据案件ID查询文书列表
     */
    List<Document> listByCaseId(Long caseId);

    /**
     * 获取详情
     */
    Document getDetail(Long id);

    /**
     * 新增文书
     */
    void addDocument(Document document);

    /**
     * 修改文书
     */
    void updateDocument(Document document);

    /**
     * 删除文书
     */
    void deleteDocument(Long id);
}