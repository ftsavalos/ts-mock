package gr.eurobank.transactions.esbmock.controller;

import com.google.common.collect.Lists;
import gr.eurobank.transactions.esbmock.model.Collateral;
import gr.eurobank.transactions.esbmock.model.UnpaidInstallment;
import gr.eurobank.transactions.esbmock.model.loan.dto.*;
import gr.eurobank.transactions.esbmock.service.EsbMockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static gr.eurobank.transactions.esbmock.controller.ControllerConstants.COLLATERAL_CODE_INSURANCE_GUARANTEE;
import static gr.eurobank.transactions.esbmock.controller.ControllerConstants.COLLATERAL_INSURANCE_SEQUENCE_NUMBER;

@RestController
@RequestMapping("/ocp-middleware/{channel}")
@Slf4j
public class LoansController {

    private final EsbMockService esbMockService;

    private static boolean loanBasicDataToggle;

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
//        return ResponseEntity.badRequest().build();
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

    @GetMapping("/loans/{loanAccountNumber}/loan-guarantees-expenses")
    public ResponseEntity loanGuaranteesExpenses() {
        LoanExpenseDetailsResponse data = this.esbMockService.getObject(LoanExpenseDetailsResponse.class);
        data.getLoanExpenseDetails().forEach(expense -> {
            expense.setDischarged(false);
            expense.setExpenseRecupFlag("5");
            expense.setExpenseCode("940");
        });
        return this.esbMockService.convertObjectToResponseEntity(data);
    }

    @PostMapping("loans/{loanAccountNumber}/expenses-discharge")
    public ResponseEntity loanExpensesDisharge() {
        return this.esbMockService.getSuccessResponse();
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

    @PutMapping("loans/{loanAccountNumber}/pricing-policy")
    public ResponseEntity updatePricingPolicy() {
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("loans/{loanAccountNumber}/pricing-policy")
    public ResponseEntity registerNewPricingPolicy() {
        return this.esbMockService.getSuccessResponse();
    }

    @GetMapping("loans/{loanAccountNumber}/pricing-policies")
    public ResponseEntity getPricingPolicies() {
        SpecialPricingsResponse data = this.esbMockService.getObject(SpecialPricingsResponse.class);
        data.getPricingPolicies().get(0).setPricingPolicyCode("114");
        return this.esbMockService.convertObjectToResponseEntity(data);
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
//        loanBasicDataToggle = !loanBasicDataToggle;
        if (loanBasicDataToggle) {
            LoanBasicDataResponse loanBasicData = new LoanBasicDataResponse(BigDecimal.TEN, BigDecimal.TEN, BigDecimal.TEN);
            return this.esbMockService.convertObjectToResponseEntity(loanBasicData);
        } else {
            return this.esbMockService.getErrorResponse("UGE0029", "Ο Λογαριασμος ειναι ακυρωμενος η ανενεργος!!!");
        }
    }

    @GetMapping("loans/{loanAccountNumber}/loan-basic-data-totals")
    public ResponseEntity loanBasicDataTotals() {
        return this.loanBasicData();
    }

    @PostMapping("loans/{loanAccountNumber}/collect-total-delay-bill")
    public ResponseEntity collectTotalDelayBill() {
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("loans/{loanAccountNumber}/early-loan-repayment")
    public ResponseEntity earlyLoanRepayment() {
        return this.esbMockService.getSuccessResponse();
    }

    @GetMapping("loans/{loanAccountNumber}/written-off-loans")
    public ResponseEntity writtenOffLoans() {
        WrittenOffLoansResponse writtenOffLoans = new WrittenOffLoansResponse(
                new BigDecimal("0"),
                new BigDecimal("60"),
                new BigDecimal("100")
        );
        return this.esbMockService.convertObjectToResponseEntity(writtenOffLoans);
    }

    @PostMapping("loans/{loanAccountNumber}/written-off-loans-collection")
    public ResponseEntity writtenOffLoansCollection() {
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("loans/{loanAccountNumber}/suspension")
    public ResponseEntity insertLoanSuspension() {
        return this.esbMockService.getSuccessResponse();
    }

    @GetMapping("loans/{loanAccountNumber}/unpaid-installments")
    public ResponseEntity getUnpaidInstallments() {
        Calendar oldest = Calendar.getInstance();
        oldest.set(2014, Calendar.FEBRUARY, 12);
        UnpaidInstallment ui1 = new UnpaidInstallment(oldest.getTime(), new BigDecimal("2.23"), new BigDecimal("100.34"), new BigDecimal("0.23"), new BigDecimal("10.13"));

        Calendar old = Calendar.getInstance();
        old.set(2015, Calendar.MARCH, 12);
        UnpaidInstallment ui2 = new UnpaidInstallment(old.getTime(), new BigDecimal("2.23"), new BigDecimal("100.34"), new BigDecimal("0.23"), new BigDecimal("10.13"));

        Calendar current = Calendar.getInstance();
        current.set(2015, Calendar.DECEMBER, 12);
        UnpaidInstallment ui3 = new UnpaidInstallment(current.getTime(), new BigDecimal("2.23"), new BigDecimal("100.34"), new BigDecimal("0.23"), new BigDecimal("10.13"));

        List<UnpaidInstallment> unpaidInstallments = Lists.newArrayList(ui3, ui2, ui1);
        GetUnpaidInstallmentsResponse getUnpaidInstallmentsResponse = new GetUnpaidInstallmentsResponse();
        getUnpaidInstallmentsResponse.setBusinessUnit("0023");
        getUnpaidInstallmentsResponse.setCurrentDate(Calendar.getInstance().getTime());
        getUnpaidInstallmentsResponse.setUnpaidInstallments(unpaidInstallments);
        return this.esbMockService.convertObjectToResponseEntity(getUnpaidInstallmentsResponse);
    }

    @DeleteMapping("loans/{loanAccountNumber}/dynamic-installments")
    public ResponseEntity deleteDynamicInstallments() {
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("loans/{loanAccountNumber}/reschedule")
    public ResponseEntity<?> transferLoanCommitment() {
        return this.esbMockService.getSuccessResponse();
    }

    @DeleteMapping("loans/{loanAccountNumber}/reschedule")
    public ResponseEntity<?> transferLoanRelease() {
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("loans/{loanAccountNumber}/dissociate")
    public ResponseEntity<?> openLoanDissociate() {
        return this.esbMockService.getSuccessResponse();
    }

    @PostMapping("loans/{loanAccountNumber}/simulation")
    public ResponseEntity<?> openLoanSimulation() {
        return this.esbMockService.getSuccessResponse();
    }

}