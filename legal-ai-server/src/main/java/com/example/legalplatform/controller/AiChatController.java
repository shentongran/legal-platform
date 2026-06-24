package com.example.legalplatform.controller;

import com.example.legalplatform.common.Result;
import com.example.legalplatform.dto.ChatRequest;
import com.example.legalplatform.service.AiChatService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
@RequestMapping("/api/ai")
public class AiChatController {

    @Resource
    private AiChatService aiChatService;

    @PostMapping("/chat")
    public Result<String> chat(@RequestBody ChatRequest request) {
        String reply = aiChatService.chat(request.getQuestion());
        return Result.success(reply);
    }
}
