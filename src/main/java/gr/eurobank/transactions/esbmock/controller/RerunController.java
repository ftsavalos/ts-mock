package gr.eurobank.transactions.esbmock.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class RerunController {

    private final EntityManager em;
    private final RestTemplate rest;
    private final ObjectMapper jsonObjectMapper;

    @Value("${query.input-originator}")
    private String queryInputOriginator;

    public RerunController(EntityManager em, RestTemplate rest, ObjectMapper jsonObjectMapper) {
        this.em = em;
        this.rest = rest;
        this.jsonObjectMapper = jsonObjectMapper;
    }

    @GetMapping("rerun/{originator}/{requestId}")
    public ResponseEntity rerunFlow(@PathVariable String originator, @PathVariable String requestId) throws JsonProcessingException {
        log.info("\nRequest Id: {}\nOriginator: {}", requestId, originator);

        List resultList = em.createNativeQuery(queryInputOriginator)
                .setParameter(1, requestId)
                .setParameter(2, originator)
                .getResultList();

        String inputOriginator = jsonObjectMapper.writeValueAsString(resultList.get(0));

        return ResponseEntity.ok(inputOriginator);
    }

    @PostMapping("test")
    public ResponseEntity testDuplicate(@RequestBody @Valid UserData userData){
        userData.getUsers().forEach(u -> log.info("{}", u));

        System.out.println();

        boolean isListDuplicated = userData.getUsers()
                .stream()
                .map(User::getName)
                .anyMatch(name -> Collections.frequency(userData.getUsers().stream().map(User::getName).collect(Collectors.toList()), name) > 1);

        if (isListDuplicated) {
            throw new RuntimeException("Has Duplications");
        }

        return ResponseEntity.ok(userData.getUsers());
    }

    @RequestMapping(value = "/lowstartnondenounced/execute/{originator}/{correlationId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity executeLowStartNonDenouncedFlow(@RequestBody Dates requestBody, @PathVariable String originator, @PathVariable String correlationId) throws JsonProcessingException {

        System.out.println(requestBody.getTestDate());

        return ResponseEntity.ok(requestBody);
    }

    @PostMapping("annotation")
    public ResponseEntity<?> testAnnotation(@RequestBody @Valid User user) {
        return ResponseEntity.ok(user);
    }

}

@Data
class User {
    private Long id;
    private String name;
    private String comment;
}

@Data
class Dates {
    private Date testDate;
}

@Data
class UserData {
    @UniqueElements
    private List<User> users;
}


