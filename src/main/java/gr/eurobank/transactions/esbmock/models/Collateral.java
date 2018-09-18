package gr.eurobank.transactions.esbmock.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class Collateral {

    private String collateralDescr;
    private String collateralCode;
    private String compulsory;
    private String currency;
    private Integer sequenceNumber;
    private BigDecimal collateralAmount;
    private String cardata;
    private String description;
    private String loanAccount;
    private boolean inserted;
    private boolean collected;
}
