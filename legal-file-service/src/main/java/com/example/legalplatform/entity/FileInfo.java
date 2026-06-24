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

    private Long caseId;
    private String caseName;
    private String fileName;
    private String filePath;
    private String fileType;
    private Long fileSize;
    private Long uploadUserId;
    private String uploadUser;
    private LocalDateTime createTime;
}
