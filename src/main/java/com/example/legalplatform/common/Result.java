package com.example.legalplatform.common;

import lombok.Data;

@Data
public class Result<T> {
    private int code; // 200成功, 400失败, 403无权限
    private String msg;
    private T data;

    // 新增：全参构造函数（必须！否则lombok的@Data无法自动匹配参数）
    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // 成功响应（带数据）
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data);
    }

    // 重载：成功响应（无数据，仅提示）
    public static <T> Result<T> success() {
        return new Result<>(200, "操作成功", null);
    }

    // 失败响应
    public static <T> Result<T> fail(String msg) {
        return new Result<>(400, msg, null);
    }

    // 无权限响应
    public static <T> Result<T> forbidden(String msg) {
        return new Result<>(403, msg, null);
    }
}