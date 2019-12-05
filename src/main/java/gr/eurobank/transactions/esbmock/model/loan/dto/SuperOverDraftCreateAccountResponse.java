package gr.eurobank.transactions.esbmock.model.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuperOverDraftCreateAccountResponse {

    private String applNumber;
    private String mockAccountNumber;

}
