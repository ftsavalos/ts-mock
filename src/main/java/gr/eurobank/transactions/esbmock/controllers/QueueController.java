package gr.eurobank.transactions.esbmock.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;

@RestController
@RequestMapping("/ocp-middleware/{channel}")
@Slf4j
public class QueueController {

    private final JmsTemplate jmsTemplate;
    private final Queue queue;

    public QueueController(JmsTemplate jmsTemplate, Queue queue) {
        this.jmsTemplate = jmsTemplate;
        this.queue = queue;
    }

    @PostMapping("/requestResponse")
    public ResponseEntity getRequestResponse(HttpEntity<String> requestResponse) {
        String requestBody = requestResponse.getBody();
        jmsTemplate.convertAndSend(this.queue, requestBody);

        return ResponseEntity.ok(requestBody);
    }
}