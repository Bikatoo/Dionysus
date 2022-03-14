package com.bikatoo.dionysus.dionysus.interfaces.experiment.status;

import com.bikatoo.dionysus.dionysus.interfaces.experiment.Experiment;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.bikatoo.dionysus.dionysus.interfaces.experiment.status.ExperimentStateEnum.*;

public class RunningState extends AbstractExperimentState {

    public RunningState(Experiment experiment) {
        super(experiment);
    }

    @Override
    public ExperimentStateEnum getStateEnum() {
        return RUNNING;
    }

    @Override
    public Set<ExperimentStateEnum> getPossiblePostState() {
        return new HashSet<>(Arrays.asList(SUSPEND, CLOSED));
    }
}
