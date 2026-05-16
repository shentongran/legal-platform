package com.example.legalplatform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.legalplatform.entity.FileInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper extends BaseMapper<FileInfo> {
}