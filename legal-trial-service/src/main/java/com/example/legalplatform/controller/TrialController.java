package com.example.legalplatform.controller;

import com.example.legalplatform.common.Result;
import com.example.legalplatform.entity.Trial;
import com.example.legalplatform.service.TrialService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/trial")
public class TrialController {

    @Resource
    private TrialService trialService;

    @GetMapping("/list")
    public Result<List<Trial>> list() {
        return Result.success(trialService.listAll());
    }

    @GetMapping("/list/{caseId}")
    public Result<List<Trial>> listByCaseId(@PathVariable Long caseId) {
        return Result.success(trialService.listByCaseId(caseId));
    }

    @GetMapping("/{id}")
    public Result<Trial> detail(@PathVariable Long id) {
        return Result.success(trialService.getTrialById(id));
    }

    @PostMapping("/add")
    public Result<Void> add(@RequestBody Trial trial) {
        trialService.addTrial(trial);
        return Result.success();
    }

    @PutMapping("/update")
    public Result<Void> update(@RequestBody Trial trial) {
        trialService.updateTrial(trial);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        trialService.deleteTrial(id);
        return Result.success();
    }
}
