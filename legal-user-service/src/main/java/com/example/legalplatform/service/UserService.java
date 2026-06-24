package com.example.legalplatform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.legalplatform.entity.User;
import com.example.legalplatform.vo.UserVO;

public interface UserService extends IService<User> {
    UserVO login(String username, String password);
    boolean register(String username, String password, String realName);
    UserVO getUserById(Long userId);
    boolean updateProfile(User user);
}
