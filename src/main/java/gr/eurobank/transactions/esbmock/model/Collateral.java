package gr.eurobank.transactions.esbmock.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collateral that = (Collateral) o;
        return Objects.equals(collateralCode, that.collateralCode) &&
                Objects.equals(sequenceNumber, that.sequenceNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collateralCode, sequenceNumber);
    }
}
