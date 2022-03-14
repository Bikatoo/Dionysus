package com.bikatoo.dionysus.dionysus.interfaces.experiment.status;

import com.bikatoo.dionysus.dionysus.event.DomainUpdate;
import com.bikatoo.dionysus.dionysus.event.experiment.ExperimentEvents;
import com.bikatoo.dionysus.dionysus.infrastructure.exception.GlobalException;
import com.bikatoo.dionysus.dionysus.infrastructure.mapper.ExperimentMapper;
import com.bikatoo.dionysus.dionysus.infrastructure.model.ExperimentDO;
import com.bikatoo.dionysus.dionysus.interfaces.experiment.Experiment;
import org.springframework.lang.NonNull;

import static com.bikatoo.dionysus.dionysus.infrastructure.utils.PreconditionUtils.checkConditionAndThrow;
import static com.bikatoo.dionysus.dionysus.infrastructure.utils.PreconditionUtils.checkNonNullAndThrow;

public class ExperimentStateManager {

    private final ExperimentState state;

    private final Experiment experiment;

    private final ExperimentMapper mapper;

    private final ExperimentEvents events;

    public ExperimentStateManager(@NonNull Experiment experiment, @NonNull ExperimentMapper mapper, @NonNull ExperimentEvents events) {

        checkNonNullAndThrow(experiment, new GlobalException("experiment is null"));

        this.experiment = experiment;
        this.mapper = mapper;
        this.events = events;
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

    /**
     * 切换到 已创建
     */
    public void switchToCreated() {
        doSwitch(new CreatedState(experiment));
    }

    /**
     * 切换到 运行中
     */
    public void switchToRunning() {
        doSwitch(new RunningState(experiment));
    }

    /**
     * 切换到 暂停
     */
    public void switchToSuspend() {
        doSwitch(new SuspendState(experiment));
    }

    /**
     * 切换到 已结束
     */
    public void switchToClosed() {
        doSwitch(new ClosedState(experiment));
    }

    private void doSwitch(ExperimentState toState) {

        synchronized (Experiment.class) {
            // 校验
            checkConditionAndThrow(state.switchable(toState), new GlobalException("invalid state switch from [" + state.getStateEnum() + "] to ["+ toState.getStateEnum() + "]" ));

            // 实体切换
            state.switchState(toState);

            // 持久化
            mapper.update(w -> {
                w.eq(ExperimentDO::getExperimentId, experiment.getExperimentId());
                w.set(ExperimentDO::getStatus, experiment.getState());
            });

            // 事件
            events.onSwitchedState(experiment.getExperimentId(), new DomainUpdate<>(state.getStateEnum(), toState.getStateEnum()));
        }
    }
}
