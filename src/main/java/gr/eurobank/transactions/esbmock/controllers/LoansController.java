package gr.eurobank.transactions.esbmock.controllers;

import gr.eurobank.transactions.esbmock.models.Collateral;
import gr.eurobank.transactions.esbmock.models.loan.dto.*;
import gr.eurobank.transactions.esbmock.services.EsbMockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;

@RestController
@RequestMapping("/ocp-middleware/{channel}")
@Slf4j
public class LoansController {

    private static final String COLLATERAL_CODE_INSURANCE_GUARANTEE = "203";
    private static final int COLLATERAL_INSURANCE_SEQUENCE_NUMBER = 1;

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

    @PostMapping("/loans/{loanAccountNumber}/agreement")
    public ResponseEntity finalizeLoanAgrevtement() {
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
        RetrieveInsurancesResponse insurancesResponse = this.esbMockService.getObject(RetrieveInsurancesResponse.class);
        insurancesResponse.getListOfInsurances().forEach(ins -> {
            ins.setGuaranteeCode(COLLATERAL_CODE_INSURANCE_GUARANTEE);
            ins.setSequenceNumber(COLLATERAL_INSURANCE_SEQUENCE_NUMBER);
        });
        return this.esbMockService.convertObjectToResponseEntity(insurancesResponse);
    }

    @PostMapping("/loans/{loanAccountNumber}/insurances")
    public ResponseEntity insertLoanInsurance() {
        return this.esbMockService.getSuccessResponse();
    }

    @GetMapping("loans/{loanAccountNumber}/collaterals")
    public ResponseEntity getLoanAccountCollaterals() {
        ListOfCollateralsResponse listOfCollateralsResponse = new ListOfCollateralsResponse(
                Arrays.asList(
                        this.esbMockService.getObject(Collateral.class),
                        this.esbMockService.getObject(Collateral.class),
                        this.esbMockService.getObject(Collateral.class)
                )
        );

        listOfCollateralsResponse.getListOfCollaterals().forEach(a -> log.info("{}", a));
        listOfCollateralsResponse.getListOfCollaterals().forEach(col -> {
            col.setCollateralCode(COLLATERAL_CODE_INSURANCE_GUARANTEE);
            col.setSequenceNumber(COLLATERAL_INSURANCE_SEQUENCE_NUMBER);
        });

        return this.esbMockService.convertObjectToResponseEntity(listOfCollateralsResponse);
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
        LoanExpenseDetailsResponse data = this.esbMockService.getObject(LoanExpenseDetailsResponse.class);
        data.getLoanExpenseDetails().forEach(expense ->  {
            expense.setDischarged(false);
            expense.setExpenseRecupFlag("5");
            expense.setExpenseCode("250");
        });
        return this.esbMockService.convertObjectToResponseEntity(data);
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
                new BigDecimal("10"),
                new BigDecimal("0"),
                new BigDecimal("30"),
                new BigDecimal("20"),
                new BigDecimal("0"));

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

    @PostMapping("/loans/{loanAccountNumber}/manage-parameters")
    public ResponseEntity manageParametersRequest() {
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("/loans/{loanAccountNumber}/credit-scoring")
    public ResponseEntity creditScoring() {
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("/loans/{loanAccountNumber}/merchant-credit")
    public ResponseEntity handleMerchantCredit() {
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("loans/{loanAccountNumber}/expenses-partial-discharge")
    public ResponseEntity loanExpensesPartialDischarge() {
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("loans/{loanAccountNumber}/contact-person")
    public ResponseEntity contactNumber() {
        return this.esbMockService.getSuccessResponse();
    }

    @GetMapping("loans/{loanAccountNumber}/loan-basic-data")
    public ResponseEntity loanBasicData() {
        return this.esbMockService.getResponseEntityByClass(LoanBasicDataResponse.class);
    }

    @PostMapping("loans/{loanAccountNumber}/collect-total-delay-bill")
    public ResponseEntity collectTotalDelayBill() {
        return this.esbMockService.getSuccessResponse();
    }
}