package gr.eurobank.transactions.esbmock.controller;

import gr.eurobank.transactions.esbmock.service.EsbMockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ocp-middleware/{channel}")
public class CardsController {

    private final EsbMockService esbMockService;

    public CardsController(EsbMockService esbMockService) {
        this.esbMockService = esbMockService;
    }

    @PostMapping("/cards/{contractNumber}/link-cards")
    public ResponseEntity<?> assignmentOfCardsToContract() {
        return esbMockService.getSuccessResponse();
    }

    @PostMapping("/cards/{contractNumber}/finalize-contract")
    public ResponseEntity<?> finalizeContract() {
        return esbMockService.getSuccessResponse();
    }

}
