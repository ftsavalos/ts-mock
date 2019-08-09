package gr.eurobank.transactions.esbmock.model;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class Property {

    private String guaranteeCode;
    private Integer sequenceNumber;
    private Integer realEstateCode;
    private Integer burdenSequenceNumber;
    private String clientCollaborator;
    private BigDecimal appraisalCost;
}
