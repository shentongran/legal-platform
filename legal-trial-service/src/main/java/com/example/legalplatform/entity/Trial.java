package com.example.legalplatform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("trial")
public class Trial {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long caseId;
    private String court;
    private LocalDateTime trialTime;
    private String judge;
    private String status;
    private String result;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
