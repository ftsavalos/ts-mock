package gr.eurobank.transactions.esbmock.controllers;

import gr.eurobank.transactions.esbmock.models.AccountingDateResponse;
import gr.eurobank.transactions.esbmock.services.EsbMockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/ocp-middleware/{channel}")
@Slf4j
public class DatesController {

    private final EsbMockService esbMockService;
    private final RestTemplate restTemplate;
    @Value("${endpoint.esb.host}${endpoint.esb.dates.accounting}")
    private String accountingDateUrl;

    public DatesController(EsbMockService esbMockService, RestTemplate restTemplate) {
        this.esbMockService = esbMockService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/dates/accounting")
    public ResponseEntity getAccountingDates() {
//        AccountingDateResponse accountingDateResponseBody =
//                restTemplate.exchange(
//                        accountingDateUrl,
//                        HttpMethod.GET,
//                        null,
//                        new ParameterizedTypeReference<AccountingDateResponse>() {},
//                        "nfs"
//                ).getBody();
//        return this.esbMockService.convertObjectToResponseEntity(accountingDateResponseBody);
        return this.esbMockService.getResponseEntityByClass(AccountingDateResponse.class);
    }
}
