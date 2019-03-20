package gr.eurobank.transactions.esbmock.model.deposit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PreOpenResponse {

    private String accountNumber;
    private String product;
    private String productDescription;
    private String subProduct;
    private String subProductDescription;
    private String currency;
    private String currencyDescription;
    private String holdingBank;
    private String holdingBranch;
    private String accountingEntity;
    private String accountingBranch;
}
