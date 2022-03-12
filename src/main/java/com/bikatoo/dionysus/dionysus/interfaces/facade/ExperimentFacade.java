package com.bikatoo.dionysus.dionysus.interfaces.facade;

import com.bikatoo.dionysus.dionysus.interfaces.DTO.CommonResult;
import com.bikatoo.dionysus.dionysus.interfaces.req.ExperimentCreateReq;
import com.bikatoo.dionysus.dionysus.service.ExperimentService;
import com.bikatoo.dionysus.dionysus.service.serviceparams.CreateExperiment;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
