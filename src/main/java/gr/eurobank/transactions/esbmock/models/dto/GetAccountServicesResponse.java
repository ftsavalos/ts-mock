package gr.eurobank.transactions.esbmock.models.dto;

import gr.eurobank.transactions.esbmock.models.AccountService;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetAccountServicesResponse {

    List<AccountService> accountServices;
}
