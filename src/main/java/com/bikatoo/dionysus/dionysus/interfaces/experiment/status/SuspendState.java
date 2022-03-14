package com.bikatoo.dionysus.dionysus.interfaces.experiment.status;

import com.bikatoo.dionysus.dionysus.interfaces.experiment.Experiment;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.bikatoo.dionysus.dionysus.interfaces.experiment.status.ExperimentStateEnum.*;

public class SuspendState extends AbstractExperimentState {

    public SuspendState(Experiment experiment) {
        super(experiment);
    }

    @Override
    public ExperimentStateEnum getStateEnum() {
        return SUSPEND;
    }

    @Override
    public Set<ExperimentStateEnum> getPossiblePostState() {
        return new HashSet<>(Arrays.asList(RUNNING, CLOSED));
    }
}
