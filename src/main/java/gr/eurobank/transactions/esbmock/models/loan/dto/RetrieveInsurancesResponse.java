package gr.eurobank.transactions.esbmock.models.loan.dto;

import gr.eurobank.transactions.esbmock.models.Insurance;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class RetrieveInsurancesResponse {

    private List<Insurance> listOfInsurances;
}
