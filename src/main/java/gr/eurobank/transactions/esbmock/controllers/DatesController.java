package gr.eurobank.transactions.esbmock.controllers;

import gr.eurobank.transactions.esbmock.models.AccountingDateResponse;
import gr.eurobank.transactions.esbmock.services.EsbMockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ocp-middleware/{channel}")
@Slf4j
public class DatesController {

    private final EsbMockService esbMockService;

    public DatesController(EsbMockService esbMockService) {
        this.esbMockService = esbMockService;
    }

    @GetMapping("/dates/accounting")
    public ResponseEntity getAccountingDates() {
        return this.esbMockService.getResponseEntityByClass(AccountingDateResponse.class);
    }
}
