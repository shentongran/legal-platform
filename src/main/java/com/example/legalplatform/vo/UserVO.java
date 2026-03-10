package com.example.legalplatform.vo;

import lombok.Data;

// 返回给前端的用户信息（隐藏敏感字段）
@Data
public class UserVO {
    private Long id;
    private String username;
    private String role; // 角色：admin/judge/party
}