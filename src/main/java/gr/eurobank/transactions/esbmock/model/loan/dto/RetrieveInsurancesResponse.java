package gr.eurobank.transactions.esbmock.model.loan.dto;

import gr.eurobank.transactions.esbmock.model.Insurance;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class RetrieveInsurancesResponse {

    private List<Insurance> listOfInsurances;
}
