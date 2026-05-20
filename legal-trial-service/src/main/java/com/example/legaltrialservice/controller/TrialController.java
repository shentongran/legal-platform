package com.example.legaltrialservice.controller;

import com.example.legaltrialservice.entity.Trial;
import com.example.legaltrialservice.service.TrialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trial")
public class TrialController {

    @Autowired
    private TrialService trialService;

    // 根据案件ID查询庭审列表
    @GetMapping("/list/{caseId}")
    public List<Trial> listByCaseId(@PathVariable Long caseId) {
        return trialService.listByCaseId(caseId);
    }

    // 庭审详情
    @GetMapping("/{id}")
    public Trial detail(@PathVariable Long id) {
        return trialService.getDetail(id);
    }

    // 新增庭审
    @PostMapping("/add")
    public String add(@RequestBody Trial trial) {
        trialService.addTrial(trial);
        return "新增庭审成功";
    }

    // 修改庭审
    @PutMapping("/update")
    public String update(@RequestBody Trial trial) {
        trialService.updateTrial(trial);
        return "修改庭审成功";
    }

    // 删除庭审
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        trialService.deleteTrial(id);
        return "删除庭审成功";
    }
}