package com.bikatoo.dionysus.dionysus.interfaces.experiment;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@NoArgsConstructor
public class Experiment {

    private Long experimentId;
    private String name;
    private String description;
    private ExperimentStatus status;
    private Set<String> blacklist;
    private Set<String> whitelist;
    private BigDecimal percentage;
    private String version;
}
