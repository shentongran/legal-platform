package com.example.legalplatform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.legalplatform.entity.FileInfo;
import com.example.legalplatform.mapper.FileMapper;
import com.example.legalplatform.service.FileService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, FileInfo> implements FileService {

    /**
     * 获取所有文件列表
     */
    @Override
    public List<FileInfo> getFileList() {
        return list();
    }

    /**
     * 新增：根据用户ID查询自己的卷宗（权限核心）
     */
    @Override
    public List<FileInfo> listByUserId(Long userId) {
        LambdaQueryWrapper<FileInfo> wrapper = new LambdaQueryWrapper<>();
        // 如果 userId 为空（游客），返回空列表
        if (userId == null) {
            return List.of();
        }
        // 只查询当前用户上传的卷宗
        wrapper.eq(FileInfo::getUploadUser, userId.toString());
        return list(wrapper);
    }

    /**
     * 上传文件（保存信息到数据库）
     */
    @Override
    public void uploadFile(FileInfo fileInfo) {
        fileInfo.setCreateTime(LocalDateTime.now());
        save(fileInfo);
    }

    /**
     * 删除文件（根据ID）
     */
    @Override
    public void deleteFile(Long id) {
        removeById(id);
    }
}