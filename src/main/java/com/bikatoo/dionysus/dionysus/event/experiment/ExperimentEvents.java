package com.bikatoo.dionysus.dionysus.event.experiment;

import com.bikatoo.dionysus.dionysus.event.DomainUpdate;
import com.bikatoo.dionysus.dionysus.event.Events;
import com.bikatoo.dionysus.dionysus.infrastructure.model.ExperimentDO;

public interface ExperimentEvents extends Events<ExperimentEvent> {

    void publish(ExperimentEvent event);

    void onCreated(Long experimentId, ExperimentDO experiment);

    void onUpdated(Long experimentId, DomainUpdate<ExperimentDO> update);

    void onDeleted(Long experimentId);

}
