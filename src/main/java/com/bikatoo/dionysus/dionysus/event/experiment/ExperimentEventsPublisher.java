package com.bikatoo.dionysus.dionysus.event.experiment;

import com.bikatoo.dionysus.dionysus.event.DomainUpdate;
import com.bikatoo.dionysus.dionysus.infrastructure.model.ExperimentDO;
import com.bikatoo.dionysus.dionysus.interfaces.experiment.status.ExperimentStateEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Component
@Log4j2
public class ExperimentEventsPublisher implements ExperimentEvents {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public void publish(ExperimentEvent event) {
        event.setEventId(UUID.randomUUID());
        event.setTriggerAt(new Date());
        try {
            rabbitTemplate.convertAndSend("dionysus.experiment.exchange", "experiment.created", new ObjectMapper().writeValueAsString(event));
        } catch (JsonProcessingException e) {
            log.error("event 序列化失败");
        }
    }

    @Override
    public void onCreated(Long experimentId, ExperimentDO experiment) {

        ExperimentCreatedEvent event = new ExperimentCreatedEvent();
        event.setExperimentId(experimentId);
        event.setExperiment(experiment);
        publish(event);
    }

    @Override
    public void onUpdated(Long experimentId, DomainUpdate<ExperimentDO> update) {

        ExperimentUpdatedEvent event = new ExperimentUpdatedEvent();
        event.setExperimentId(experimentId);
        event.setUpdate(update);
        publish(event);
    }

    @Override
    public void onDeleted(Long experimentId) {
        ExperimentDeletedEvent event = new ExperimentDeletedEvent();
        event.setExperimentId(experimentId);
        publish(event);
    }

    @Override
    public void onSwitchedState(Long experimentId, DomainUpdate<ExperimentStateEnum> update) {
        ExperimentStateSwitchedEvent event = new ExperimentStateSwitchedEvent();
        event.setExperimentId(experimentId);
        event.setUpdate(update);
        publish(event);
    }
}
