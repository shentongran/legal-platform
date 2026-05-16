package com.example.legalplatform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.legalplatform.entity.FileInfo;
import java.util.List;

public interface FileService extends IService<FileInfo> {

    List<FileInfo> getFileList();

    // 新增：根据用户ID查询卷宗（权限过滤）
    List<FileInfo> listByUserId(Long userId);

    void uploadFile(FileInfo fileInfo);
    void deleteFile(Long id);
}