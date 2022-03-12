package com.bikatoo.dionysus.dionysus.service.serviceparams;

import com.bikatoo.dionysus.dionysus.interfaces.experiment.ExperimentStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class CreateExperiment {

    private String name;
    private String description;
    private ExperimentStatus status;
    private Set<String> blacklist;
    private Set<String> whitelist;
    private BigDecimal percentage;
    private String version;
}
