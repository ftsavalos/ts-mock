package gr.eurobank.transactions.esbmock.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class Property {

    private String guaranteeCode;
    private Integer sequenceNumber;
    private Integer realEstateCode;
    private Integer burdenSequenceNumber;
    private String clientCollaborator;
    private BigDecimal appraisalCost;
}
