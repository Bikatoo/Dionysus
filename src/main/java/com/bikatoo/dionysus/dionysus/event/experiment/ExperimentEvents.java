package com.bikatoo.dionysus.dionysus.event.experiment;

import com.bikatoo.dionysus.dionysus.event.DomainUpdate;
import com.bikatoo.dionysus.dionysus.event.Events;
import com.bikatoo.dionysus.dionysus.infrastructure.model.ExperimentDO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ExperimentEvents extends Events<ExperimentEvent> {

    void publish(ExperimentEvent event) throws JsonProcessingException;

    void onCreated(Long experimentId, ExperimentDO experiment);

    void onUpdated(Long experimentId, DomainUpdate<ExperimentDO> update);

    void onDeleted(Long experimentId);

}
