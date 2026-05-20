package com.example.legaltrialservice.entity;

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
    private Long caseId;          // 对应 case_id
    private String court;        // 法院
    private LocalDateTime trialTime; // 对应 trial_time
    private String judge;        // 法官
    private String status;       // 状态
    private String result;       // 结果
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}