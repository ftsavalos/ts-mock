package gr.eurobank.transactions.esbmock.controllers;

import gr.eurobank.transactions.esbmock.models.dto.*;
import gr.eurobank.transactions.esbmock.services.EsbMockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import javax.jms.Queue;
import java.math.BigDecimal;

@RestController
@RequestMapping("/ocp-middleware/{channel}")
@Slf4j
public class EsbMockController {

    private final EsbMockService esbMockService;
    private final JmsTemplate jmsTemplate;
    private final Queue queue;

    public EsbMockController(EsbMockService esbMockService, JmsTemplate jmsTemplate, Queue queue) {
        this.esbMockService = esbMockService;
        this.jmsTemplate = jmsTemplate;
        this.queue = queue;
    }

    @PostMapping("/loans/{loanAccountNumber}/early-partial-repayment")
    public ResponseEntity getEarlyPartialRepayment() {
        log.info("POST to : /early-partial-repayment");
        return this.esbMockService.getResponseData(EarlyPartialRepaymentResponse.class);
    }

    @GetMapping("/loans/{loanAccountNumber}/fire-insurances")
    public ResponseEntity getInsurances() {
        log.info("GET  to : /fire-insurances");

        GetFireInsurancesResponse data = this.esbMockService.getObject(GetFireInsurancesResponse.class);
        data.getListOfFireInsurances().forEach(ins -> ins.setStatusIndicator("3"));

        return this.esbMockService.getResponseObject(data);
    }

    @PostMapping("/loans/{loanAccountNumber}/fire-insurance-collection")
    public ResponseEntity collectFireInsurance() {
        log.info("POST to : /fire-insurances-collection");
        return this.esbMockService.getSuccessResponse();
    }

    @GetMapping("/loans/{loanAccountNumber}/properties")
    public ResponseEntity getLoanAccountProperties() {
        log.info("GET  to : /properties");
        return this.esbMockService.getResponseData(LoanAccountPropertiesResponse.class);
    }

    @PostMapping("/loans/{loanAccountNumber}/properties")
    public ResponseEntity insertLoanProperties() {
        log.info("POST to : /properties");
        return this.esbMockService.getSuccessResponse();
    }

    @DeleteMapping("/loans/{loanAccountNumber}/properties")
    public ResponseEntity deleteLoanAccountProperties() {
        log.info("DELETE  : /properties");

        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("/loans")
    public ResponseEntity createLoanAccount() {
        log.info("POST to : /loans");
        return this.esbMockService.getResponseData(LoanAccountResponse.class);
    }

    @PostMapping("/loans/{loanAccountNumber}/aggreement")
    public ResponseEntity finalizeLoanAggreement() {
        log.info("POST to : /aggreement");
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("/loans/{loanAccountNumber}/flexi-request")
    public ResponseEntity InsertFlexiRequest() {
        log.info("POST to : /flexi-request");
        return this.esbMockService.getSuccessResponse();
    }

    @GetMapping("/loans/{loanAccountNumber}/source-code-info")
    public ResponseEntity getSourceCodeInfo() {
        log.info("GET  to : /source-code-info");
        return this.esbMockService.getResponseData(InsertSourceCodeInfoResponse.class);
    }

    @PostMapping("/loans/{loanAccountNumber}/source-code-info")
    public ResponseEntity insertSourceCodeInfo() {
        log.info("POST to : /source-code-info");
        return this.esbMockService.getResponseData(InsertSourceCodeInfoResponse.class);
    }

    @GetMapping("/loans/{loanAccountNumber}/insurances")
    public ResponseEntity getLoanAccountInsurances() {
        log.info("GET  to : /insurances");
        return this.esbMockService.getResponseData(RetrieveInsurancesResponse.class);
    }

    @PostMapping("/loans/{loanAccountNumber}/insurances")
    public ResponseEntity insertLoanInsurance() {
        log.info("POST to : /insurances");
        return this.esbMockService.getSuccessResponse();
    }

    @GetMapping("loans/{loanAccountNumber}/collaterals")
    public ResponseEntity getLoanAccountCollaterals() {
        log.info("GET  to : /collaterals");
        return this.esbMockService.getResponseData(ListOfCollateralsResponse.class);
    }

    @PostMapping("loans/{loanAccountNumber}/collaterals")
    public ResponseEntity insertLoanCollaterals() {
        log.info("POST to : /collaterals");
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("/loans/{loanAccountNumber}/approval")
    public ResponseEntity insertLoanApproval() {
        log.info("POST to : /approval");
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("loans/{loanAccountNumber}/debt-forgiveness")
    public ResponseEntity loanDeptForgiveness() {
        log.info("POST to : /debt-forgiveness");
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("loans/{loanAccountNumber}/expenses-discharge")
    public ResponseEntity loanExpensesDisharge() {
        log.info("POST to : /expenses-discharge");
        return this.esbMockService.getSuccessResponse();
    }

    @GetMapping("/loans/{loanAccountNumber}/loan-guarantees-expenses")
    public ResponseEntity loanGuaranteesExpenses() {
        log.info("POST to : /loan-guarantees-expenses");
        return this.esbMockService.getResponseData(LoanExpenseDetailsResponse.class);
    }

    @PostMapping("loans/{loanAccountNumber}/participants")
    public ResponseEntity insertLoanParticipant() {
        log.info("POST to : /participants");
        return this.esbMockService.getResponseData(InsertLoanParticipantResponse.class);
    }

    @PostMapping("/loans/{loanAccountNumber}/permanent-delay-collection")
    public ResponseEntity loanPermanentDelayCollection() {
        log.info("POST to : /permanent-delay-collection");
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("/loans/{loanAccountNumber}/permanent-delay-close")
    public ResponseEntity loanPermanentDelayClose() {
        log.info("POST to : /permanent-delay-close");
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("/loans/{loanAccountNumber}/low-income")
    public ResponseEntity setLowIncomeDetails() {
        log.info("POST to : /low-income");
        return this.esbMockService.getSuccessResponse();
    }

    @GetMapping("/loans/{loanAccountNumber}/overdue-debt")
    public ResponseEntity getOverDueDept() {
        log.info("POST to : /overdue-debt");
        GetOverdueDebtResponse data = new GetOverdueDebtResponse(
                new BigDecimal(0.2),
                new BigDecimal(0.2),
                new BigDecimal(0.2),
                new BigDecimal(0.2),
                new BigDecimal(0));

        return this.esbMockService.getResponseObject(data);
    }

    @PostMapping("/loans/{loanAccountNumber}/capitalization")
    public ResponseEntity interestCapitalization() {
        log.info("POST to : /capitalization");
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("loans/{loanAccountNumber}/pricing-policy")
    public ResponseEntity registerNewPricingPolicy() {
        log.info("POST to : /pricing-policy");
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("loans/{loanAccountNumber}/private-contract")
    public ResponseEntity loanIssueOfPrivateContract() {
        log.info("POST to : /private-contract");
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("loans/{loanAccountNumber}/specialized-administration")
    public ResponseEntity loanSpecializedAdministration() {
        log.info("POST to : /specialized-administration");
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("loans/{loanAccountNumber}/tte-notification")
    public ResponseEntity insertBankOfGreeceNotification() {
        log.info("POST to : /tte-notification");
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("/requestResponse")
    public ResponseEntity getRequestResponse(HttpEntity<String> requestResponse) {
        String requestBody = requestResponse.getBody();
        log.info("POST to : /requestResponse");

        jmsTemplate.convertAndSend(this.queue, requestBody);

        return ResponseEntity.ok(requestBody);
    }

    @GetMapping("/accounts/{accountNumber}/steps")
    public ResponseEntity accoutnOpeningSteps() {
        log.info("GET  to : /steps");
        OpenAccountStepsResponse data = new OpenAccountStepsResponse("NNNNNNNNNNNNNNNNN", "96", "EUROHOME BALLOON 5 ΧΡ. ΣΤΑΘΕΡΟ", "3954", "001002003004040041005042043007008034044082009010013", "P", "ΔΑΝΕΙΑ", "YYYYYYYYYYYYYYYYY");
        return this.esbMockService.getResponseObject(data);
    }

    @PostMapping("/accounts/money-transfer")
    public ResponseEntity moneyTransferBetweenAccounts() {
        log.info("POST to: /money-transfer");
        return this.esbMockService.getSuccessResponse();
    }

    @GetMapping("/accounts/{accountNumber}/services")
    public ResponseEntity getAccountServices() {
        log.info("GET to: /services");
        return this.esbMockService.getResponseData(GetAccountServicesResponse.class);
    }

    @PutMapping("/loans/{loanAccountNumber}/servicing-account")
    public ResponseEntity updateLoanServicingAccount() {
        log.info("PUT to : /servicing-account");
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("/accounts/{accountNumber}/participants")
    public ResponseEntity addAccountParticipant() {
        log.info("POST to: /participants");
        return this.esbMockService.getSuccessResponse();
    }

    @GetMapping("/accounts/{accountNumber}/balance")
    public ResponseEntity getAccountBalance() {
        log.info("GET to: /balance");
        return this.esbMockService.getResponseData(AccountBalanceResponse.class);
    }
}