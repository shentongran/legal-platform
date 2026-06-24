package com.example.legalplatform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.legalplatform.entity.FileInfo;
import com.example.legalplatform.mapper.FileMapper;
import com.example.legalplatform.service.FileService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, FileInfo> implements FileService {

    @Override
    public List<FileInfo> listByUserId(Long userId) {
        LambdaQueryWrapper<FileInfo> wrapper = new LambdaQueryWrapper<>();
        if (userId == null) {
            return List.of();
        }
        wrapper.eq(FileInfo::getUploadUserId, userId)
               .orderByDesc(FileInfo::getCreateTime);
        return list(wrapper);
    }

    @Override
    public List<FileInfo> listByCaseId(Long caseId) {
        LambdaQueryWrapper<FileInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FileInfo::getCaseId, caseId)
               .orderByDesc(FileInfo::getCreateTime);
        return list(wrapper);
    }

    @Override
    public void uploadFile(FileInfo fileInfo) {
        fileInfo.setCreateTime(LocalDateTime.now());
        save(fileInfo);
    }

    @Override
    public void deleteFile(Long id) {
        removeById(id);
    }
}
