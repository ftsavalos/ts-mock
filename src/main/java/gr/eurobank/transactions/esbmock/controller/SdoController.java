package gr.eurobank.transactions.esbmock.controller;

import gr.eurobank.transactions.esbmock.service.EsbMockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ocp-middleware/{channel}")
@Slf4j
public class SdoController {

    private final EsbMockService esbMockService;

    public SdoController(EsbMockService esbMockService) {
        this.esbMockService = esbMockService;
    }

    @PostMapping("sdo/{loanAccountNumber}/collaterals")
    public ResponseEntity addCollateralCMS() {
        return this.esbMockService.getSuccessResponse();
    }

    @DeleteMapping("sdo/{loanAccountNumber}/collaterals")
    public ResponseEntity deleteCollateralCMS() {
        return this.esbMockService.getSuccessResponse();
    }
}
