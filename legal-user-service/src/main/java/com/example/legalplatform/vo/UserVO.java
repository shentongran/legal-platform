package com.example.legalplatform.vo;

import lombok.Data;

@Data
public class UserVO {
    private Long id;
    private String username;
    private String nickname;
    private String email;
    private String phone;
    private String role;
    private String avatar;
    private String token; // 登录成功返回的JWT令牌
}