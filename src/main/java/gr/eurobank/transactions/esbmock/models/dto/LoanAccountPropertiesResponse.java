package gr.eurobank.transactions.esbmock.models.dto;

import gr.eurobank.transactions.esbmock.models.Property;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class LoanAccountPropertiesResponse {

    List<Property> listOfProperties;

}
