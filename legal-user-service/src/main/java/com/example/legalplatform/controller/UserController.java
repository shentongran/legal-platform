package com.example.legalplatform.controller;

import com.example.legalplatform.entity.User;
import com.example.legalplatform.mapper.UserMapper;
import com.example.legalplatform.common.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    // 登录
    @PostMapping("/login")
    public Result<User> login(@RequestBody User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, user.getUsername());
        wrapper.eq(User::getPassword, user.getPassword());
        User loginUser = userMapper.selectOne(wrapper);

        if (loginUser == null) {
            return Result.error(500, "用户名或密码错误");
        }
        return Result.success(loginUser);
    }

    // 注册
    @PostMapping("/register")
    public Result<User> register(@RequestBody User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, user.getUsername());
        User exist = userMapper.selectOne(wrapper);

        if (exist != null) {
            return Result.error(500, "用户名已存在");
        }

        user.setRole("USER");
        userMapper.insert(user);
        return Result.success(user);
    }

    // 获取个人信息
    @GetMapping("/profile")
    public Result<User> profile(@RequestParam Integer userId) {
        User user = userMapper.selectById(userId);
        return Result.success(user);
    }

    // 修改信息
    @PostMapping("/update")
    public Result<User> update(@RequestBody User user) {
        User oldUser = userMapper.selectById(user.getId());
        if (oldUser == null) {
            return Result.error(500, "用户不存在");
        }
        oldUser.setPhone(user.getPhone());
        oldUser.setEmail(user.getEmail());
        oldUser.setRealName(user.getRealName());
        userMapper.updateById(oldUser);
        return Result.success(oldUser);
    }
}