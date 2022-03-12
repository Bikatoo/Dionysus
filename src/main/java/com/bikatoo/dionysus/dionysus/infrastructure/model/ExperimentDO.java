package com.bikatoo.dionysus.dionysus.infrastructure.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bikatoo.dionysus.dionysus.interfaces.experiment.ExperimentStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
@TableName(value = "experiment")
public class ExperimentDO {

    @TableId(value = "experiment_id", type = IdType.AUTO)
    private Long experimentId;

    private String name;
    private String description;
    private ExperimentStatus status;
    private Set<String> blacklist;
    private Set<String> whitelist;
    private BigDecimal percentage;
    private String version;
}
