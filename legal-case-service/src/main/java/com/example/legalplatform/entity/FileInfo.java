package com.example.legalplatform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("file_info")
public class FileInfo {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String caseName;      // 案件名称
    private String fileName;      // 原始文件名
    private String filePath;      // 相对路径 / 或完整路径
    private String fileType;      // 文件类型
    private String uploadUser;    // 上传人
    private LocalDateTime createTime; // 上传时间
}