package gr.eurobank.transactions.esbmock.models.deposit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NumberAccountResponse {

    private String accountNumber;
    private String bankCode;
    private String branchCode;
    private String accountMain;
    private String checkDigits;
}
