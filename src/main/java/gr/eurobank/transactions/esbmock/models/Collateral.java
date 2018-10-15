package gr.eurobank.transactions.esbmock.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
public class Collateral implements Serializable {

    private String collateralDescr;
    private String collateralType;
    private String collateralCode;
    private String collateralHolder;
    private String compulsory;
    private String currency;
    private Integer sequenceNumber;
    private BigDecimal collateralAmount;
    private String cardata;
    private String description;
    private String loanAccount;
}
