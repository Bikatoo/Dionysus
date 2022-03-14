package com.bikatoo.dionysus.dionysus.event;

import java.util.UUID;

/**
 * 领域事件抽象
 */
public interface DomainEvent extends Event {

    UUID getEventId();
}
