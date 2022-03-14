package com.bikatoo.dionysus.dionysus.event.experiment;

import com.bikatoo.dionysus.dionysus.event.DomainUpdate;
import com.bikatoo.dionysus.dionysus.interfaces.experiment.status.ExperimentStateEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExperimentStateSwitchedEvent extends ExperimentEvent {

    private DomainUpdate<ExperimentStateEnum> update;
}
