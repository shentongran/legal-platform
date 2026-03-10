package com.example.legalplatform.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.legalplatform.dto.LoginRequestDTO;
import com.example.legalplatform.entity.User;
import com.example.legalplatform.service.UserService;
import com.example.legalplatform.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 用户模块接口
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 登录接口
    @PostMapping("/login")
    public UserVO login(@RequestBody LoginRequestDTO loginRequest) {
        // 1. 查询用户（根据用户名）
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, loginRequest.getUsername());
        User user = userService.getOne(queryWrapper);

        // 2. 验证密码（简单版：明文对比，毕业设计够用；生产需加密）
        if (user == null || !user.getPassword().equals(loginRequest.getPassword())) {
            throw new RuntimeException("用户名或密码错误");
        }

        // 3. 转换为VO返回（隐藏密码等敏感信息）
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }
}