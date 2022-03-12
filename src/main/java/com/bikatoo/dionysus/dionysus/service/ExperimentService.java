package com.bikatoo.dionysus.dionysus.service;

import com.bikatoo.dionysus.dionysus.service.serviceparams.CreateExperiment;
import com.bikatoo.dionysus.dionysus.service.serviceparams.UpdateExperiment;

public interface ExperimentService {

    Long create(CreateExperiment create);

    void delete(Long experimentId);

    void update(UpdateExperiment update);
}
