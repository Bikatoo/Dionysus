package com.bikatoo.dionysus.dionysus.event;

import java.util.UUID;

public interface DomainEvent extends Event {

    UUID getEventId();
}
