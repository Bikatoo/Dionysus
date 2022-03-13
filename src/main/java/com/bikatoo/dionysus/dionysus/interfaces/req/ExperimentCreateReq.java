package com.bikatoo.dionysus.dionysus.interfaces.req;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExperimentCreateReq {

    private String name;
    private String description;
    private String blacklist;
    private String whitelist;
    private BigDecimal percentage;
}
