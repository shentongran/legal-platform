package com.example.legalplatform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.legalplatform.entity.FileInfo;
import java.util.List;

public interface FileService extends IService<FileInfo> {

    List<FileInfo> listByUserId(Long userId);

    List<FileInfo> listByCaseId(Long caseId);

    void uploadFile(FileInfo fileInfo);

    void deleteFile(Long id);
}
