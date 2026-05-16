package com.example.legalaiserver.service;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.legalaiserver.config.AiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AiChatService {

    @Autowired
    private AiConfig aiConfig;

    public String chat(String question) {
        Map<String, Object> body = new HashMap<>();
        body.put("model", aiConfig.getModel());

        List<Map<String, String>> messages = new ArrayList<>();

        Map<String, String> systemMsg = new HashMap<>();
        systemMsg.put("role", "system");
        systemMsg.put("content",
                "你是专业法律AI助手。\n" +
                        "1. 回答必须简洁、精炼、三句话到五句话结束。\n" +
                        "2. 绝对不要使用任何 ** 、* 、加粗符号、markdown格式。\n" +
                        "3. 只用纯文本，口语化，通俗易懂。\n" +
                        "4. 只回答法律相关问题，无关问题请拒绝。"
        );

        Map<String, String> userMsg = new HashMap<>();
        userMsg.put("role", "user");
        userMsg.put("content", question);

        messages.add(systemMsg);
        messages.add(userMsg);

        body.put("messages", messages);
        body.put("temperature", 0.3);

        try (HttpResponse response = HttpRequest.post(aiConfig.getApiUrl())
                .header("Authorization", "Bearer " + aiConfig.getApiKey())
                .header("Content-Type", "application/json")
                .body(JSON.toJSONString(body))
                .timeout(30000)
                .execute()) {

            String resBody = response.body();
            JSONObject json = JSON.parseObject(resBody);

            String answer = json.getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content")
                    .trim();

            return answer.replace("*", "").replace("**", "");

        } catch (Exception e) {
            e.printStackTrace();
            return "AI服务繁忙，请稍后再试。";
        }
    }
}