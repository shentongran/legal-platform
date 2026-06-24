package com.example.legalplatform.controller;

import com.example.legalplatform.common.Result;
import com.example.legalplatform.dto.LoginRequestDTO;
import com.example.legalplatform.entity.User;
import com.example.legalplatform.service.UserService;
import com.example.legalplatform.vo.UserVO;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Result<UserVO> login(@RequestBody LoginRequestDTO request) {
        UserVO userVO = userService.login(request.getUsername(), request.getPassword());
        if (userVO == null) {
            return Result.error("用户名或密码错误");
        }
        return Result.success(userVO);
    }

    @PostMapping("/register")
    public Result<Void> register(@RequestBody User user) {
        if (user.getUsername() == null || user.getPassword() == null) {
            return Result.error("用户名和密码不能为空");
        }
        boolean success = userService.register(user.getUsername(), user.getPassword(), user.getRealName());
        if (!success) {
            return Result.error("用户名已存在");
        }
        return Result.success();
    }

    @GetMapping("/profile")
    public Result<UserVO> profile(@RequestParam Long userId) {
        UserVO userVO = userService.getUserById(userId);
        if (userVO == null) {
            return Result.error("用户不存在");
        }
        return Result.success(userVO);
    }

    @PutMapping("/update")
    public Result<Void> update(@RequestBody User user) {
        if (user.getId() == null) {
            return Result.error("用户ID不能为空");
        }
        boolean success = userService.updateProfile(user);
        if (!success) {
            return Result.error("用户不存在");
        }
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<UserVO> getById(@PathVariable Long id) {
        UserVO userVO = userService.getUserById(id);
        if (userVO == null) {
            return Result.error("用户不存在");
        }
        return Result.success(userVO);
    }
}
