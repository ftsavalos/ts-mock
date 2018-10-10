package gr.eurobank.transactions.esbmock.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.jms.Topic;

@RestController
@RequestMapping("/ocp-middleware/{channel}")
@Slf4j
public class QueueController {

    private final JmsTemplate jmsTemplate;
    private final Queue queue;
    private final Topic topic;

    public QueueController(JmsTemplate jmsTemplate, Queue queue, Topic topic) {
        this.jmsTemplate = jmsTemplate;
        this.queue = queue;
        this.topic = topic;
    }

    @PostMapping("/requestResponse")
    public ResponseEntity getRequestResponse(HttpEntity<String> requestResponse) {
        String requestBody = requestResponse.getBody();
        jmsTemplate.convertAndSend(this.queue, requestBody);
        jmsTemplate.convertAndSend(this.topic, requestBody);

        return ResponseEntity.ok(requestBody);
    }
}