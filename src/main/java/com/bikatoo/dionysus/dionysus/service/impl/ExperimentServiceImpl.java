package com.bikatoo.dionysus.dionysus.service.impl;

import com.bikatoo.dionysus.dionysus.event.DomainUpdate;
import com.bikatoo.dionysus.dionysus.event.experiment.ExperimentEvents;
import com.bikatoo.dionysus.dionysus.infrastructure.converter.ExperimentConverter;
import com.bikatoo.dionysus.dionysus.infrastructure.exception.GlobalException;
import com.bikatoo.dionysus.dionysus.infrastructure.mapper.ExperimentMapper;
import com.bikatoo.dionysus.dionysus.infrastructure.model.ExperimentDO;
import com.bikatoo.dionysus.dionysus.interfaces.experiment.Experiment;
import com.bikatoo.dionysus.dionysus.interfaces.experiment.status.ExperimentStateEnum;
import com.bikatoo.dionysus.dionysus.interfaces.experiment.ExperimentVersion;
import com.bikatoo.dionysus.dionysus.interfaces.experiment.status.ExperimentStateManager;
import com.bikatoo.dionysus.dionysus.service.ExperimentService;
import com.bikatoo.dionysus.dionysus.service.serviceparams.CreateExperiment;
import com.bikatoo.dionysus.dionysus.service.serviceparams.UpdateExperiment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.bikatoo.dionysus.dionysus.infrastructure.utils.PreconditionUtils.checkNonNullAndThrow;

@Service
@Slf4j
public class ExperimentServiceImpl implements ExperimentService {

    @Resource
    private ExperimentMapper experimentMapper;

    @Resource
    private ExperimentEvents experimentEvents;

    @Resource
    private ExperimentConverter experimentConverter;

    @Override
    public Long create(CreateExperiment create) {

        ExperimentDO data = new ExperimentDO();
        BeanUtils.copyProperties(create, data);
        data.setStatus(ExperimentStateEnum.CREATED);
        data.setVersion(ExperimentVersion.DEFAULT_VERSION.toString());
        experimentMapper.insert(data);

        experimentEvents.onCreated(data.getExperimentId(), data);
        return data.getExperimentId();
    }

    @Override
    public void delete(Long experimentId) {

        ExperimentDO experiment = experimentMapper.selectOne(w -> w.eq(ExperimentDO::getExperimentId, experimentId));
        checkNonNullAndThrow(experiment, new GlobalException("实验不存在", "实验不存在"));
        experimentMapper.delete(w -> w.eq(ExperimentDO::getExperimentId, experimentId));

        experimentEvents.onDeleted(experimentId);
    }

    @Override
    public void update(Long experimentId, UpdateExperiment update) {
        ExperimentDO before = experimentMapper.selectOne(w -> w.eq(ExperimentDO::getExperimentId, experimentId));
        checkNonNullAndThrow(before, new GlobalException("实验不存在", "实验不存在"));

        ExperimentDO after = new ExperimentDO();
        BeanUtils.copyProperties(before, after);
        after.setName(update.getName());
        after.setDescription(update.getDescription());
        after.setBlacklist(update.getBlacklist());
        after.setWhitelist(update.getWhitelist());
        after.setPercentage(update.getPercentage());
        experimentMapper.update(after, w -> w.eq(ExperimentDO::getExperimentId, experimentId));

        experimentEvents.onUpdated(experimentId, new DomainUpdate<>(before, after));
    }

    @Override
    public void switchToRunning(Long experimentId) {
        ExperimentStateManager stateManager = buildStateManager(experimentId);
        stateManager.switchToRunning();
    }

    @Override
    public void switchToSuspend(Long experimentId) {
        ExperimentStateManager stateManager = buildStateManager(experimentId);
        stateManager.switchToSuspend();
    }

    @Override
    public void switchToClosed(Long experimentId) {
        ExperimentStateManager stateManager = buildStateManager(experimentId);
        stateManager.switchToClosed();
    }

    private ExperimentStateManager buildStateManager(Long experimentId) {
        ExperimentDO experimentDO = experimentMapper.selectOne(w -> w.eq(ExperimentDO::getExperimentId, experimentId));
        checkNonNullAndThrow(experimentDO, new GlobalException("实验不存在", "实验不存在"));

        Experiment experiment = experimentConverter.DO2Entity(experimentDO);
        return new ExperimentStateManager(experiment, experimentMapper, experimentEvents);
    }
}
