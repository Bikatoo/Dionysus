package com.bikatoo.dionysus.dionysus.event.experiment;

import com.bikatoo.dionysus.dionysus.event.DomainUpdate;
import com.bikatoo.dionysus.dionysus.infrastructure.model.ExperimentDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExperimentUpdatedEvent extends ExperimentEvent {

    private DomainUpdate<ExperimentDO> update;

}
