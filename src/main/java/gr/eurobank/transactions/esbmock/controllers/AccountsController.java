package gr.eurobank.transactions.esbmock.controllers;

import gr.eurobank.transactions.esbmock.models.deposit.dto.NumberAccountResponse;
import gr.eurobank.transactions.esbmock.models.deposit.dto.PreOpenResponse;
import gr.eurobank.transactions.esbmock.models.loan.dto.AccountBalanceResponse;
import gr.eurobank.transactions.esbmock.models.loan.dto.GetAccountServicesResponse;
import gr.eurobank.transactions.esbmock.models.loan.dto.OpenAccountStepsResponse;
import gr.eurobank.transactions.esbmock.services.EsbMockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@RestController
@RequestMapping("/ocp-middleware/{channel}")
@Slf4j
public class AccountsController {

    private final EsbMockService esbMockService;

    public AccountsController(EsbMockService esbMockService) {
        this.esbMockService = esbMockService;
    }

    @GetMapping("/accounts/{accountNumber}/steps")
    public ResponseEntity accoutnOpeningSteps() {
        OpenAccountStepsResponse data = new OpenAccountStepsResponse("NNNNNNNNNNNNNNNNN", "96", "EUROHOME BALLOON 5 ΧΡ. ΣΤΑΘΕΡΟ", "3954", "001002003004040041005042043007008034044082009010013", "P", "ΔΑΝΕΙΑ", "YYYYYYYYYYYYYYYYY");
        return this.esbMockService.convertObjectToResponseEntity(data);
    }

    @PostMapping("/accounts/money-transfer")
    public ResponseEntity moneyTransferBetweenAccounts() {
        return this.esbMockService.getSuccessResponse();
    }

    @GetMapping("/accounts/{accountNumber}/services")
    public ResponseEntity getAccountServices() {
        return this.esbMockService.getResponseEntityByClass(GetAccountServicesResponse.class);
    }

    @PostMapping("/accounts/{accountNumber}/participants")
    public ResponseEntity addAccountParticipant() {
        return this.esbMockService.getSuccessResponse();
    }

    @GetMapping("/accounts/{accountNumber}/balance")
    public ResponseEntity getAccountBalance() {
        AccountBalanceResponse data = this.esbMockService.getObject(AccountBalanceResponse.class);
        data.setAvailableBalance(new BigDecimal("0.1112"));

        return ResponseEntity.ok(data);
    }

    @PostMapping("/accounts/contract-number")
    public ResponseEntity contractNumberAssignment() {
        return this.esbMockService.getResponseEntityByClass(NumberAccountResponse.class);
    }

    @PostMapping("/accounts/{accountNumber}/contract")
    public ResponseEntity newAccountContractIssue() {
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("/accounts/{accountNumber}/categorization")
    public ResponseEntity newAccountCategorization(HttpServletRequest request) {
        return this.esbMockService.getResponseEntityByClass(PreOpenResponse.class);
    }

    @PostMapping("/accounts/{accountNumber}/formalization")
    public ResponseEntity newAccountFormalization() {
        return this.esbMockService.getSuccessResponse();
    }
}
