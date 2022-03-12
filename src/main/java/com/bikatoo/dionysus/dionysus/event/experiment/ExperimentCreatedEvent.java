package com.bikatoo.dionysus.dionysus.event.experiment;

import com.bikatoo.dionysus.dionysus.infrastructure.model.ExperimentDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExperimentCreatedEvent extends ExperimentEvent {

    private ExperimentDO experiment;

}
