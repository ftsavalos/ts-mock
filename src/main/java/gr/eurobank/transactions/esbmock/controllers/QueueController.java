package gr.eurobank.transactions.esbmock.controllers;

import com.jayway.jsonpath.JsonPath;
import gr.eurobank.transactions.esbmock.History;
import gr.eurobank.transactions.esbmock.HistoryRepository;
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
    private final HistoryRepository historyRepository;


    public QueueController(JmsTemplate jmsTemplate, Queue queue, Topic topic, HistoryRepository historyRepository) {
        this.jmsTemplate = jmsTemplate;
        this.queue = queue;
        this.topic = topic;
        this.historyRepository = historyRepository;
    }

    @PostMapping("/requestResponse")
    public ResponseEntity getRequestResponse(HttpEntity<String> requestResponse) {
        String requestBody = requestResponse.getBody();
        jmsTemplate.convertAndSend(this.queue, requestBody);
        jmsTemplate.convertAndSend(this.topic, requestBody);


        String requestId = JsonPath.read(requestBody, "$.data.processId.requestId");
        History history = new History(requestId);
        this.historyRepository.save(history);

        log.info("Request ID: {}", requestId);

        return ResponseEntity.ok(requestBody);
    }
}