package com.bikatoo.dionysus.dionysus.event;

public interface Events<E extends Event> {

    void publish(E event);
}
