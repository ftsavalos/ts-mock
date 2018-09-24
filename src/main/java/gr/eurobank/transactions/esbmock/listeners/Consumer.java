package gr.eurobank.transactions.esbmock.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Profile("queueConsumer")
public class Consumer {

    @JmsListener(destination = "${activemq.queue}")
    public void consume(String message) {
        log.info("Received Message: {}", message);
    }
}
