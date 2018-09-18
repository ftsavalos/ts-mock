package gr.eurobank.transactions.esbmock.models.dto;

import gr.eurobank.transactions.esbmock.models.FireInsurance;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@AllArgsConstructor
@Data
public class GetFireInsurancesResponse {

    private List<FireInsurance> listOfFireInsurances;

}
