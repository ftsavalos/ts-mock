package gr.eurobank.transactions.esbmock.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class AccountService implements Serializable {

    private String serviceCode;
    private String description;
    private String connectedProduct;
    private String connectedProductDetails;
}
