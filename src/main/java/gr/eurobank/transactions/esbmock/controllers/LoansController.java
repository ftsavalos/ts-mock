package gr.eurobank.transactions.esbmock.controllers;

import gr.eurobank.transactions.esbmock.models.loan.dto.*;
import gr.eurobank.transactions.esbmock.services.EsbMockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/ocp-middleware/{channel}")
@Slf4j
public class LoansController {

    private final EsbMockService esbMockService;

    public LoansController(EsbMockService esbMockService) {
        this.esbMockService = esbMockService;
    }

    @PostMapping("/loans/{loanAccountNumber}/early-partial-repayment")
    public ResponseEntity getEarlyPartialRepayment() {
        return this.esbMockService.getResponseEntityByClass(EarlyPartialRepaymentResponse.class);
    }

    @GetMapping("/loans/{loanAccountNumber}/fire-insurances")
    public ResponseEntity getInsurances() {
        GetFireInsurancesResponse data = this.esbMockService.getObject(GetFireInsurancesResponse.class);
        data.getListOfFireInsurances().forEach(ins -> ins.setStatusIndicator("3"));

        return this.esbMockService.convertObjectToResponseEntity(data);
    }

    @PostMapping("/loans/{loanAccountNumber}/fire-insurance-collection")
    public ResponseEntity collectFireInsurance() {
        return this.esbMockService.getSuccessResponse();
    }

    @GetMapping("/loans/{loanAccountNumber}/properties")
    public ResponseEntity getLoanAccountProperties() {
        return this.esbMockService.getResponseEntityByClass(LoanAccountPropertiesResponse.class);
    }

    @PostMapping("/loans/{loanAccountNumber}/properties")
    public ResponseEntity insertLoanProperties() {
        return this.esbMockService.getSuccessResponse();
    }

    @DeleteMapping("/loans/{loanAccountNumber}/properties")
    public ResponseEntity deleteLoanAccountProperties() {
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("/loans")
    public ResponseEntity createLoanAccount() {
        return this.esbMockService.getResponseEntityByClass(LoanAccountResponse.class);
    }

    @PostMapping("/loans/{loanAccountNumber}/aggreement")
    public ResponseEntity finalizeLoanAggreement() {
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("/loans/{loanAccountNumber}/flexi-request")
    public ResponseEntity InsertFlexiRequest() {
        return this.esbMockService.getSuccessResponse();
    }

    @GetMapping("/loans/{loanAccountNumber}/source-code-info")
    public ResponseEntity getSourceCodeInfo() {
        return this.esbMockService.getResponseEntityByClass(InsertSourceCodeInfoResponse.class);
    }

    @PostMapping("/loans/{loanAccountNumber}/source-code-info")
    public ResponseEntity insertSourceCodeInfo() {
        return this.esbMockService.getResponseEntityByClass(InsertSourceCodeInfoResponse.class);
    }

    @GetMapping("/loans/{loanAccountNumber}/insurances")
    public ResponseEntity getLoanAccountInsurances() {
        return this.esbMockService.getResponseEntityByClass(RetrieveInsurancesResponse.class);
    }

    @PostMapping("/loans/{loanAccountNumber}/insurances")
    public ResponseEntity insertLoanInsurance() {
        return this.esbMockService.getSuccessResponse();
    }

    @GetMapping("loans/{loanAccountNumber}/collaterals")
    public ResponseEntity getLoanAccountCollaterals() {
        return this.esbMockService.getResponseEntityByClass(ListOfCollateralsResponse.class);
    }

    @PostMapping("loans/{loanAccountNumber}/collaterals")
    public ResponseEntity insertLoanCollaterals() {
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("/loans/{loanAccountNumber}/approval")
    public ResponseEntity insertLoanApproval() {
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("loans/{loanAccountNumber}/debt-forgiveness")
    public ResponseEntity loanDeptForgiveness() {
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("loans/{loanAccountNumber}/expenses-discharge")
    public ResponseEntity loanExpensesDisharge() {
        return this.esbMockService.getSuccessResponse();
    }

    @GetMapping("/loans/{loanAccountNumber}/loan-guarantees-expenses")
    public ResponseEntity loanGuaranteesExpenses() {
        return this.esbMockService.getResponseEntityByClass(LoanExpenseDetailsResponse.class);
    }

    @PostMapping("loans/{loanAccountNumber}/participants")
    public ResponseEntity insertLoanParticipant() {
        return this.esbMockService.getResponseEntityByClass(InsertLoanParticipantResponse.class);
    }

    @PostMapping("/loans/{loanAccountNumber}/permanent-delay-collection")
    public ResponseEntity loanPermanentDelayCollection() {
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("/loans/{loanAccountNumber}/permanent-delay-close")
    public ResponseEntity loanPermanentDelayClose() {
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("/loans/{loanAccountNumber}/low-income")
    public ResponseEntity setLowIncomeDetails() {
        return this.esbMockService.getSuccessResponse();
    }

    @GetMapping("/loans/{loanAccountNumber}/overdue-debt")
    public ResponseEntity getOverDueDept() {
        GetOverdueDebtResponse data = new GetOverdueDebtResponse(
                new BigDecimal(0.2),
                new BigDecimal(0),
                new BigDecimal(0),
                new BigDecimal(0),
                new BigDecimal(0));

        return this.esbMockService.convertObjectToResponseEntity(data);
    }

    @PostMapping("/loans/{loanAccountNumber}/capitalization")
    public ResponseEntity interestCapitalization() {
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("loans/{loanAccountNumber}/pricing-policy")
    public ResponseEntity registerNewPricingPolicy() {
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("loans/{loanAccountNumber}/private-contract")
    public ResponseEntity loanIssueOfPrivateContract() {
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("loans/{loanAccountNumber}/specialized-administration")
    public ResponseEntity loanSpecializedAdministration() {
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("loans/{loanAccountNumber}/tte-notification")
    public ResponseEntity insertBankOfGreeceNotification() {
        return this.esbMockService.getSuccessResponse();
    }

    @PutMapping("/loans/{loanAccountNumber}/servicing-account")
    public ResponseEntity updateLoanServicingAccount() {
        return this.esbMockService.getSuccessResponse();
    }
}