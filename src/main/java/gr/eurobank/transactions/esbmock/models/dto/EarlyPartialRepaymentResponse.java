package gr.eurobank.transactions.esbmock.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class EarlyPartialRepaymentResponse {

    private String balanceAfterPayment;
    private String balanceBeforePayment;
    private String contractNR;
    private String exchangeRateEquality;
    private String holderBranch;
    private String interestProtectionCancelCost;
    private String loanAcc;
    private String loanAmount;
    private String loanCurrency;
    private String loanInterest;
    private String loanProtectionCost;
    private String operatingBranch;
    private String paymentAmount;
    private String penalty;
    private String protectionCost;
    private String serviceAmount;
    private String total;
    private Date transactionDate;
    private Date valueDate;
    private String yearPayments;
    private String yearPaymentsBalanceWithoutCost;

}
