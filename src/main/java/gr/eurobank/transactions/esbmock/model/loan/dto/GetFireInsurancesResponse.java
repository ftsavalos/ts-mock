package gr.eurobank.transactions.esbmock.model.loan.dto;

import gr.eurobank.transactions.esbmock.model.FireInsurance;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@AllArgsConstructor
@Data
public class GetFireInsurancesResponse {

    private List<FireInsurance> listOfFireInsurances;

}
