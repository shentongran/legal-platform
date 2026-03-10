package com.example.legalplatform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.legalplatform.entity.User;
import com.example.legalplatform.mapper.UserMapper;
import com.example.legalplatform.service.UserService;
import org.springframework.stereotype.Service;

@Service // 交给Spring管理
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}