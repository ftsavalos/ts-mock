package gr.eurobank.transactions.esbmock.controllers;

import gr.eurobank.transactions.esbmock.models.Collateral;
import gr.eurobank.transactions.esbmock.models.Insurance;
import gr.eurobank.transactions.esbmock.services.EsbMockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.jms.Topic;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ocp-middleware/{channel}")
@Slf4j
public class QueueController {

    private final JmsTemplate jmsTemplate;
    private final Queue queue;
    private final Topic topic;
    private final EsbMockService esbMockService;
    List<Collateral> collaterals = new ArrayList<>();
    List<Insurance> insurances = new ArrayList<>();

    public QueueController(JmsTemplate jmsTemplate, Queue queue, Topic topic, EsbMockService esbMockService) {
        this.jmsTemplate = jmsTemplate;
        this.queue = queue;
        this.topic = topic;
        this.esbMockService = esbMockService;
    }

    @PostMapping("/requestResponse")
    public ResponseEntity getRequestResponse(HttpEntity<String> requestResponse) {
        String requestBody = requestResponse.getBody();
        jmsTemplate.convertAndSend(this.queue, requestBody);
        jmsTemplate.convertAndSend(this.topic, requestBody);
//
//                filteredInsurances = collaterals
//                        .stream()
//                        .flatMap(ins -> insurances
//                                .stream()
//                                .filter(col -> col.getSequenceNumber().equals(ins.getSequenceNumber())))
//                        .collect(Collectors.toList());

//        insurances.forEach(i -> this.collaterals
//                .stream()
//                .filter(c ->  c.getSequenceNumber().equals(i.getSequenceNumber()))
//                .forEach(c -> filteredInsurances.add(i)));

//        filteredInsurances = insurances
//                .stream()
//                .filter(ins -> collaterals.stream().anyMatch(col -> filterTrue(col, ins)))
//                .collect(Collectors.toList());

        return ResponseEntity.ok(requestBody);
    }
}