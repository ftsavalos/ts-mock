package gr.eurobank.transactions.esbmock.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class FireInsurance {

    private Integer realEstateCode;
    private String sequenceNumber;
    private String statusIndicator;
    private String currency;
    private String insuranceCompany;
    private Double grossAmount;
    private String contractNumber;
    private Integer contractSequenceNumber;
    private Date startDate;
    private Date endDate;
    private String renewalNumber;
    private Double fireInsuranceValue;
    private Double earthInsuranceValue;
    private Double grossContractCost;
    private Date contractCancelDate;
}
