package gr.eurobank.transactions.esbmock.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class InsertSourceCodeInfoResponse {

    private String loanAccountNumber;
    private Double downpaymentPercentage;
    private String sourceType;
    private BigDecimal commission;
    private BigDecimal minimumCommission;
    private BigDecimal spread;
    private String originCode;
    private String sourceCode;
    private String originType;
    private Boolean defaultAccountFlag;
    private Boolean defaultComSpreadFlag;

}
