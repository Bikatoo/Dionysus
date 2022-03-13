package com.bikatoo.dionysus.dionysus.interfaces.experiment;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Experiment {

    private Long experimentId;
    private String name;
    private String description;
    private ExperimentStatus status;
    private String blacklist;
    private String whitelist;
    private BigDecimal percentage;
    private ExperimentVersion version;
}
