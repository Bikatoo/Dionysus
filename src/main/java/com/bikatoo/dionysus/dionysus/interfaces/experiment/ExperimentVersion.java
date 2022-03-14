package com.bikatoo.dionysus.dionysus.interfaces.experiment;

import com.bikatoo.dionysus.dionysus.infrastructure.exception.GlobalException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.bikatoo.dionysus.dionysus.infrastructure.utils.PreconditionUtils.checkConditionAndThrow;

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

    public static ExperimentVersion valueOf(String versionStr) {

        if (versionStr == null || "".equals(versionStr)) {
            return null;
        }
        String[] versionArr = versionStr.split("\\.");
        checkConditionAndThrow(2 == versionArr.length, new GlobalException("invalid version string [" + versionStr + "]" ));
        return new ExperimentVersion(Integer.parseInt(versionArr[0]), Integer.parseInt(versionArr[1]));
    }
}
