package com.bikatoo.dionysus.dionysus.event.experiment;

import com.bikatoo.dionysus.dionysus.event.DomainEvent;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public abstract class ExperimentEvent implements DomainEvent {

    protected Long experimentId;

    protected UUID eventId;

    protected Date triggerAt;
}
