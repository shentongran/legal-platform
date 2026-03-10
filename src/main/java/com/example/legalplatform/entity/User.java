package com.example.legalplatform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data // Lombok自动生成get/set/toString
@TableName("user") // 对应数据库表名
public class User {
    @TableId(type = IdType.AUTO) // 主键自增
    private Long id;
    private String username;
    private String password;
    private String role;
    private LocalDateTime createTime;
}