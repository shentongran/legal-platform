package com.example.legaltrialservice.controller;

import com.example.legaltrialservice.entity.Trial;
import com.example.legaltrialservice.service.TrialService;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/trial")
public class TrialController {

    private final TrialService trialService;

    public TrialController(TrialService trialService) {
        this.trialService = trialService;
    }

    // 列表：前端需要的接口
    @GetMapping("/list")
    public Map<String, Object> list() {
        List<Trial> list = trialService.listAll();
        Map<String, Object> res = new HashMap<>();
        res.put("code", 200);
        res.put("data", list);
        return res;
    }

    // 按案件ID查
    @GetMapping("/list/{caseId}")
    public List<Trial> listByCaseId(@PathVariable Long caseId) {
        return trialService.listByCaseId(caseId);
    }

    // 详情
    @GetMapping("/{id}")
    public Trial detail(@PathVariable Long id) {
        return trialService.getById(id);
    }

    // 新增
    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody Trial trial) {
        trialService.add(trial);
        Map<String, Object> res = new HashMap<>();
        res.put("code", 200);
        return res;
    }

    // 修改
    @PostMapping("/update")
    public Map<String, Object> update(@RequestBody Trial trial) {
        trialService.update(trial);
        Map<String, Object> res = new HashMap<>();
        res.put("code", 200);
        return res;
    }

    // 删除
    @PostMapping("/delete")
    public Map<String, Object> delete(@RequestParam Long id) {
        trialService.delete(id);
        Map<String, Object> res = new HashMap<>();
        res.put("code", 200);
        return res;
    }
}