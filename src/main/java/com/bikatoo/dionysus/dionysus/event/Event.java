package com.bikatoo.dionysus.dionysus.event;

import java.io.Serializable;
import java.util.Date;

public interface Event extends Serializable {

    Date getTriggerAt();
}
