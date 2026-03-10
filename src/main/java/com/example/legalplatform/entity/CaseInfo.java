package com.example.legalplatform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("case_info")
public class CaseInfo {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String caseNo;
    private String caseName;
    private String partyName;
    private String evidenceDesc;
    private String status;
    private LocalDateTime createTime;
}