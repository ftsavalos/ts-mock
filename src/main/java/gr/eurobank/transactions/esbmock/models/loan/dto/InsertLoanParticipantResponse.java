package gr.eurobank.transactions.esbmock.models.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class InsertLoanParticipantResponse {

    private String participantTypeDescription;
    private String participantNumber;
    private String holderName;
    private String idTypeDescription;
    private String id;
    private String clientRepresentation;
    private String notaryName;
    private Integer attorneyNumber;
    private Date issuanceDate;
    private String description;

}
