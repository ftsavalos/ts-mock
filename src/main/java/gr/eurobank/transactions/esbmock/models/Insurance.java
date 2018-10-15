package gr.eurobank.transactions.esbmock.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@Data
public class Insurance {
    private String guaranteeCode;
    private Integer sequenceNumber;
    private String insuranceCompanyCode;
    private String certificateNumber;
    private String contractNumber;
    private String customerCif;
    private Date startDate;
    private Date endDate;
    private String comments;
    private BigDecimal contractCapital;
    private BigDecimal contractCost;
    private Double commissionPercentage;
    private String collectionModality;
}
