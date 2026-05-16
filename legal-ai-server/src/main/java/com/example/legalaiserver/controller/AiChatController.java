package com.example.legalaiserver.controller;

import com.example.legalaiserver.common.Result;
import com.example.legalaiserver.dto.ChatRequest;
import com.example.legalaiserver.service.AiChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
public class AiChatController {

    @Autowired
    private AiChatService aiChatService;

    @PostMapping("/chat")
    public Result<String> chat(@RequestBody ChatRequest request) {
        String reply = aiChatService.chat(request.getQuestion());
        return Result.success(reply);
    }
}