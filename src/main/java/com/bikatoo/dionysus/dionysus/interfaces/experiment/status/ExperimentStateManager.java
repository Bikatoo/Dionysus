package com.bikatoo.dionysus.dionysus.interfaces.experiment.status;

import com.bikatoo.dionysus.dionysus.infrastructure.exception.GlobalException;
import com.bikatoo.dionysus.dionysus.interfaces.experiment.Experiment;

import static com.bikatoo.dionysus.dionysus.infrastructure.utils.PreconditionUtils.checkConditionAndThrow;
import static com.bikatoo.dionysus.dionysus.infrastructure.utils.PreconditionUtils.checkNonNullAndThrow;

public class ExperimentStateManager {

    private final ExperimentState state;

    private final Experiment experiment;

    public ExperimentStateManager(Experiment experiment) {

        checkNonNullAndThrow(experiment, new GlobalException("experiment is null"));

        this.experiment = experiment;
        switch (experiment.getState()) {
            case CREATED:
                this.state = new CreatedState(experiment);
                break;
            case RUNNING:
                this.state = new RunningState(experiment);
                break;
            case SUSPEND:
                this.state = new SuspendState(experiment);
                break;
            case CLOSED:
                this.state = new ClosedState(experiment);
                break;
            default:
                throw new GlobalException("invalid experiment state");
        }
    }

    public void switchToCreated() {
        doSwitch(new CreatedState(experiment));
    }

    public void switchToRunning() {
        doSwitch(new RunningState(experiment));
    }

    public void switchToSuspend() {
        doSwitch(new SuspendState(experiment));
    }

    public void switchToClosed() {
        doSwitch(new ClosedState(experiment));
    }

    private void doSwitch(ExperimentState toState) {
        checkConditionAndThrow(state.switchable(toState), new GlobalException("invalid state switch from [" + state.getStateEnum() + "] to ["+ toState.getStateEnum() + "]" ));

        state.switchState(toState);
        // TODO 触发事件
        // TODO 持久化
    }
}
