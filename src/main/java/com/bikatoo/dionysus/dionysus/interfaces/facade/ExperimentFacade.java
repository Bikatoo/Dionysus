package com.bikatoo.dionysus.dionysus.interfaces.facade;

import com.bikatoo.dionysus.dionysus.interfaces.DTO.CommonResult;
import com.bikatoo.dionysus.dionysus.interfaces.req.ExperimentCreateReq;
import com.bikatoo.dionysus.dionysus.service.ExperimentService;
import com.bikatoo.dionysus.dionysus.service.serviceparams.CreateExperiment;
import com.bikatoo.dionysus.dionysus.service.serviceparams.UpdateExperiment;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class ExperimentFacade {

    @Resource
    private ExperimentService experimentService;

    @PostMapping("/experiment")
    public CommonResult<Long> create(@RequestBody ExperimentCreateReq req) {

        CreateExperiment create = new CreateExperiment();
        BeanUtils.copyProperties(req, create);
        Long id = experimentService.create(create);
        return CommonResult.ok(id);
    }

    @DeleteMapping("/experiment/{experimentId}")
    public CommonResult<Void> delete(@PathVariable Long experimentId) {
        experimentService.delete(experimentId);
        return CommonResult.ok();
    }

    @PutMapping("/experiment/{experimentId}")
    public CommonResult<Void> update(@PathVariable Long experimentId,
                                     @RequestBody ExperimentCreateReq req) {

        UpdateExperiment update = new UpdateExperiment();
        BeanUtils.copyProperties(req, update);
        experimentService.update(experimentId, update);
        return CommonResult.ok();
    }

    @PutMapping("/experiment/{experimentId}/state/running")
    public CommonResult<Void> switchToRunning(@PathVariable Long experimentId) {
        experimentService.switchToRunning(experimentId);
        return CommonResult.ok();
    }

    @PutMapping("/experiment/{experimentId}/state/suspend")
    public CommonResult<Void> switchToSuspend(@PathVariable Long experimentId) {
        experimentService.switchToSuspend(experimentId);
        return CommonResult.ok();
    }

    @PutMapping("/experiment/{experimentId}/state/closed")
    public CommonResult<Void> switchToClosed(@PathVariable Long experimentId) {
        experimentService.switchToClosed(experimentId);
        return CommonResult.ok();
    }


}
