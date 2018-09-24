package gr.eurobank.transactions.esbmock.models.loan.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class GetOverdueDebtResponse {

    private String loanAccountNumber;
    private String name;
    private String status;
    private String statusDescription;
    private Date denounceDate;
    private Date date;
    private String loanCurrency;
    private Double delayRateCur;
    private Double delayRateGRD;
    private final BigDecimal newLegal;
    private final BigDecimal newFire;
    private Double exchangeRate;
    private String currency2;
    private BigDecimal loanBalance;
    private BigDecimal oldLegalFire;
    private BigDecimal receivedMemo;
    private BigDecimal capitalOverdueGRD;
    private BigDecimal accrualsMemo;
    private BigDecimal accruedOverdueOld;
    private BigDecimal capitalOverdueCur;
    private BigDecimal accruedOverdue;
    private String currency3;
    private final BigDecimal totalLoanCur;
    private final BigDecimal totalGRD;
    private String currency4;
    private BigDecimal collectLoanCur;
    private BigDecimal collectGRD;
    private BigDecimal demandForecastAmount;
    private String localCur;
    private BigDecimal totalAmountGRD;
    private BigDecimal demandWriteOffAmount;
    private BigDecimal totalAmountEur;
    private Double forecastAmountPerc;
    private Double writtenOffAmountPerc;
    private BigDecimal entityOvercost;

    private final BigDecimal loanBalanceWithMemoSum;

}


