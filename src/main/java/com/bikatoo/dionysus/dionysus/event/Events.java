package com.bikatoo.dionysus.dionysus.event;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface Events<E extends Event> {

    void publish(E event) throws JsonProcessingException;
}
