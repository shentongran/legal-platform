package com.example.legalplatform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.legalplatform.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper // 标识为MyBatis映射器
public interface UserMapper extends BaseMapper<User> {
    // 继承BaseMapper后，自动拥有CRUD方法，无需写SQL
}