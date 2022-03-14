package com.bikatoo.dionysus.dionysus.infrastructure.converter;

import com.bikatoo.dionysus.dionysus.infrastructure.model.ExperimentDO;
import com.bikatoo.dionysus.dionysus.interfaces.experiment.Experiment;

import com.bikatoo.dionysus.dionysus.interfaces.experiment.ExperimentVersion;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ExperimentConverter implements Converter<Experiment, ExperimentDO> {

    @Override
    public ExperimentDO entity2DO(Experiment e) {

        if (e == null) {
            return null;
        }
        ExperimentDO d = new ExperimentDO();
        BeanUtils.copyProperties(e, d);
        if (e.getVersion() != null) {
            d.setVersion(e.getVersion().toString());
        }
        return d;
    }

    @Override
    public Experiment DO2Entity(ExperimentDO d) {

        if (d == null) {
            return null;
        }

        Experiment e = new Experiment();
        BeanUtils.copyProperties(e, d);
        if (d.getVersion() != null && !"".equals(d.getVersion())) {
            e.setVersion(ExperimentVersion.valueOf(d.getVersion()));
        }
        return e;
    }
}
