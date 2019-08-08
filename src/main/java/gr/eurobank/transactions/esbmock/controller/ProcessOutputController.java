package gr.eurobank.transactions.esbmock.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import gr.eurobank.transactions.esbmock.service.ProcessOutputService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.jms.Topic;

@RestController
@RequestMapping("/ocpm/services")
@Slf4j
public class ProcessOutputController {

    private final JmsTemplate jmsTemplate;
    private final Queue queue;
    private final Topic topic;
    private final ProcessOutputService processOutputService;

    public ProcessOutputController(JmsTemplate jmsTemplate, Queue queue, Topic topic, ProcessOutputService processOutputService) {
        this.jmsTemplate = jmsTemplate;
        this.queue = queue;
        this.topic = topic;
        this.processOutputService = processOutputService;
    }

    @PostMapping("stm/internal/flow-execution-response/{0}")
    public ResponseEntity getRequestResponse(@RequestBody String requestBody) throws JsonProcessingException {
        try {
            jmsTemplate.convertAndSend(this.queue, requestBody);
            jmsTemplate.convertAndSend(this.topic, requestBody);
        } catch (JmsException e) {
            log.info("Nothing to worry about...! ftsavalos is using a redundant Active MQ.");
        }

//        processOutputService.saveHistory(requestBody);

        return ResponseEntity.ok(requestBody);
    }
}