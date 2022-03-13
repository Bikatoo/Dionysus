package com.bikatoo.dionysus.dionysus.service.serviceparams;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateExperiment {

    private String name;
    private String description;
    private String blacklist;
    private String whitelist;
    private BigDecimal percentage;
}
