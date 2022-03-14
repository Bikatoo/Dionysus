package com.bikatoo.dionysus.dionysus.interfaces.experiment.status;

import com.bikatoo.dionysus.dionysus.interfaces.experiment.Experiment;

public interface ExperimentState {

    boolean switchable(ExperimentState toState);

    void switchState(ExperimentState toState);

    ExperimentStateEnum getStateEnum();

    Experiment getExperiment();
}
