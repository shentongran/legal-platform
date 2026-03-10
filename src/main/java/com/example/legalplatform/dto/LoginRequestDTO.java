package com.example.legalplatform.dto;

import lombok.Data;

// 接收前端登录请求的参数
@Data
public class LoginRequestDTO {
    private String username; // 用户名
    private String password; // 密码
}