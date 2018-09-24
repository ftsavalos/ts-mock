package gr.eurobank.transactions.esbmock.models.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@Data
public class EarlyPartialRepaymentResponse {

    private BigDecimal balanceAfterPayment;
    private BigDecimal balanceBeforePayment;
    private BigDecimal capital;
    private BigDecimal comission;
    private String comMethod;
    private String comMethodDescr;
    private String contractNR;
    private String currency;
    private BigDecimal efte;
    private BigDecimal exchangeRate;
    private BigDecimal exchangeRateEquality;
    private String holderBranch;
    private BigDecimal interest;
    private String interestProtectionCancelCost;
    private String linkedCurrency;
    private String loanAcc;
    private String loanAccount;
    private BigDecimal loanAmount;
    private String loanCurrency;
    private BigDecimal loanInterest;
    private BigDecimal loanProtectionCost;
    private String operatingBranch;
    private BigDecimal paymentAmount;
    private BigDecimal penalty;
    private BigDecimal protectionCost;
    private String protectionInterestCost;
    private String serviceAccount;
    private String serviceCurrency;
    private BigDecimal total;
    private Date transactionDate;
    private Date valueDate;
    private String yearPayments;
    private String yearPaymentsBalanceWithoutCost;

}
