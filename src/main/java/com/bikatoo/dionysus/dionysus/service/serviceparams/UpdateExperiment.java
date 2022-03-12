package com.bikatoo.dionysus.dionysus.service.serviceparams;

import com.bikatoo.dionysus.dionysus.interfaces.experiment.ExperimentStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateExperiment {

    private String name;
    private String description;
    private ExperimentStatus status;
    private String blacklist;
    private String whitelist;
    private BigDecimal percentage;
    private String version;
}
