package gr.eurobank.transactions.esbmock.models.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
public class AccountBalanceResponse {

    private BigDecimal availableBalance;
    private String subProductDescription;
    private BigDecimal accountingBalance;
    private String currencyDescription;
    private String currency;
    private String centralRestrictedAccount;
    private String identificationNumber;
    private String contactName;
    private BigDecimal withholdedAmount;
    private String responsibleBranch;
    private BigDecimal accountLimit;
    private String product;
    private String subProduct;
    private Date openingDate;
}
