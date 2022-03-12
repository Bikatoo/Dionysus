package com.bikatoo.dionysus.dionysus.infrastructure.amqp.listener;

import com.bikatoo.dionysus.dionysus.event.experiment.ExperimentCreatedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ExperimentListener {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "dionysus.experiment.queue.created"),
            exchange = @Exchange(value = "dionysus.experiment.exchange"),
            key = "experiment.created"))
    public void created(String msg) throws JsonProcessingException {

        JsonNode jsonNode = new ObjectMapper().readTree(msg);
        // TODO
        log.info("收到消息 {}", msg);
    }
}
