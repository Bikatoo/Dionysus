package com.bikatoo.dionysus.dionysus.interfaces.experiment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperimentVersion {

    @JsonIgnore
    public static final ExperimentVersion DEFAULT_VERSION = new ExperimentVersion(0, 1);

    // 代表实验状态 0 CREATED 1 RUNNING 2 SUSPEND 3 CLOSED
    private Integer first;

    // 每修改一次 +1 初始为1
    private Integer last;

    @Override
    public String toString() {
        return first + "." + last;
    }
}
