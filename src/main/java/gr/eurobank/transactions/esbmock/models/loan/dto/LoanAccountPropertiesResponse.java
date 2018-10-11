package gr.eurobank.transactions.esbmock.models.loan.dto;

import gr.eurobank.transactions.esbmock.models.Property;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class LoanAccountPropertiesResponse {

    private List<Property> listOfProperties;

}
