package gr.eurobank.transactions.esbmock.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Profile("consumerEnabled")
public class Consumer {

    @JmsListener(destination = "${activemq.topic}")
    public void consumeFromTopic(String message) {
        log.info("Received from Topic: {}", message);
    }

    @JmsListener(destination = "${activemq.queue}")
    public void consumeFromQueue(String message) {
        log.info("Received from queue: {}", message);
    }
}
