package com.bikatoo.dionysus.dionysus.interfaces.experiment.status;

import com.bikatoo.dionysus.dionysus.interfaces.experiment.Experiment;

import java.util.HashSet;
import java.util.Set;

public class ClosedState extends AbstractExperimentState {

    public ClosedState(Experiment experiment) {
        super(experiment);
    }

    @Override
    public ExperimentStateEnum getStateEnum() {
        return ExperimentStateEnum.CLOSED;
    }

    @Override
    public Set<ExperimentStateEnum> getPossiblePostState() {
        return new HashSet<>();
    }
}
