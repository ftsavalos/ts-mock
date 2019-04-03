package gr.eurobank.transactions.esbmock.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@Data
public class FireInsurance {

    private Integer realEstateCode;
    private String sequenceNumber;
    private String statusIndicator;
    private String currency;
    private String insuranceCompany;
    private BigDecimal grossAmount;
    private String contractNumber;
    private Integer contractSequenceNumber;
    private Date startDate;
    private Date endDate;
    private String renewalNumber;
    private BigDecimal fireInsuranceValue;
    private BigDecimal earthInsuranceValue;
    private BigDecimal grossContractCost;
    private Date contractCancelDate;
    private BigDecimal insurableValue;
    private BigDecimal netContractCost;
    private BigDecimal contractCommission;
    private BigDecimal fireInsuranceCommission;
    private BigDecimal partiallyCollectedAmount;
    private BigDecimal amountDue;
}
