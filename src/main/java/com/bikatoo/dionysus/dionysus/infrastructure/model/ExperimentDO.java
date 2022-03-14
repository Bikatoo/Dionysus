package com.bikatoo.dionysus.dionysus.infrastructure.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bikatoo.dionysus.dionysus.interfaces.experiment.status.ExperimentStateEnum;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@TableName(value = "experiment")
public class ExperimentDO implements Serializable {

    @TableId(value = "experiment_id", type = IdType.AUTO)
    private Long experimentId;

    private String name;
    private String description;
    private ExperimentStateEnum status;
    private String blacklist;
    private String whitelist;
    private BigDecimal percentage;
    private String version;
}
