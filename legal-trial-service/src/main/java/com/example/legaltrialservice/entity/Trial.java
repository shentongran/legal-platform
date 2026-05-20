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
    private Long caseId;         // 关联案件ID
    private String court;        // 法院名称
    private LocalDateTime trialTime; // 庭审时间
    private String judge;         // 法官
    private String status;        // 状态：未开庭/已开庭/已结束
    private String result;        // 庭审结果
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}