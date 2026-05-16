package com.example.legalplatform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

@Data
@TableName("case_info")
public class CaseInfo {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String caseNo;          // 对应 case_no
    private String caseName;        // 对应 case_name
    private String partyName;       // 对应 party_name
    private String evidenceDesc;    // 对应 evidence_desc
    private String status;          // 对应 status
    private Long userId;            // 对应 user_id
    private Date createTime;        // 对应 create_time
    private Date updateTime;        // 对应 update_time
}