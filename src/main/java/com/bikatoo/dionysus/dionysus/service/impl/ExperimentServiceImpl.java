package com.bikatoo.dionysus.dionysus.service.impl;

import com.bikatoo.dionysus.dionysus.infrastructure.exception.GlobalException;
import com.bikatoo.dionysus.dionysus.infrastructure.mapper.ExperimentMapper;
import com.bikatoo.dionysus.dionysus.infrastructure.model.ExperimentDO;
import com.bikatoo.dionysus.dionysus.service.ExperimentService;
import com.bikatoo.dionysus.dionysus.service.serviceparams.CreateExperiment;
import com.bikatoo.dionysus.dionysus.service.serviceparams.UpdateExperiment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.bikatoo.dionysus.dionysus.infrastructure.utils.PreconditionUtils.checkConditionAndThrow;

@Service
@Slf4j
public class ExperimentServiceImpl implements ExperimentService {

    @Resource
    private ExperimentMapper experimentMapper;

    @Override
    public Long create(CreateExperiment create) {

        ExperimentDO data = new ExperimentDO();
        BeanUtils.copyProperties(create, data);
        experimentMapper.insert(data);

        return data.getExperimentId();
    }

    @Override
    public void delete(Long experimentId) {

        boolean exists = experimentMapper.exists(w -> w.eq(ExperimentDO::getExperimentId, experimentId));
        checkConditionAndThrow(exists, new GlobalException("实验不存在", "实验不存在"));
        experimentMapper.delete(w -> w.eq(ExperimentDO::getExperimentId, experimentId));

        log.info("experiment [{}] deleted", experimentId);
    }

    @Override
    public void update(UpdateExperiment update) {

    }
}
