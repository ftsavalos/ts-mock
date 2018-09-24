package gr.eurobank.transactions.esbmock.models.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class LoanAccountResponse {

    private String loanAccountNumber;
    private String subproductDescription;


    private String durationDescription;

    private String purposeDescription;
    private String languageDescription;
    private Date applicationReceiveDate;
    private String loanCharacterizationDescription;
}
