package com.bikatoo.dionysus.dionysus.interfaces.experiment.status;

import com.bikatoo.dionysus.dionysus.interfaces.experiment.Experiment;
import lombok.Getter;
import org.springframework.lang.NonNull;

import java.util.Set;

public abstract class AbstractExperimentState implements ExperimentState {

    @Getter
    private final Experiment experiment;

    public AbstractExperimentState(Experiment experiment) {
        this.experiment = experiment;
    }

    @Override
    public boolean switchable(ExperimentState toState) {
        return getPossiblePostState().contains(toState.getStateEnum());
    }

    @Override
    public void switchState(ExperimentState toState) {
        Experiment toExperiment = toState.getExperiment();
        toExperiment.setState(toState.getStateEnum());
    }

    @NonNull
    public abstract Set<ExperimentStateEnum> getPossiblePostState();
}
