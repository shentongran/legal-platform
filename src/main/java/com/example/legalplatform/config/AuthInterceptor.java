package com.example.legalplatform.config;

import com.alibaba.fastjson.JSON;
import com.example.legalplatform.common.Result;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 获取请求头中的角色
        String role = request.getHeader("role");
        String requestURI = request.getRequestURI();

        // 2. 新增案件：仅admin可访问
        if ("/case/add".equals(requestURI)) {
            if (!"admin".equals(role)) {
                response.setContentType("application/json;charset=UTF-8");
                response.setStatus(403);
                // 明确指定泛型类型<Object>，避免推断失败
                Result<Object> result = Result.forbidden("无权限操作：仅管理员可新增案件");
                String json = JSON.toJSONString(result);
                response.getWriter().write(json);
                return false;
            }
        }

        // 3. 更新状态：仅admin/judge可访问
        if (requestURI.startsWith("/case/updateStatus/")) {
            if (!"admin".equals(role) && !"judge".equals(role)) {
                response.setContentType("application/json;charset=UTF-8");
                response.setStatus(403);
                // 明确指定泛型类型<Object>
                Result<Object> result = Result.forbidden("无权限操作：仅管理员/法官可更新案件状态");
                String json = JSON.toJSONString(result);
                response.getWriter().write(json);
                return false;
            }
        }

        return true;
    }
}