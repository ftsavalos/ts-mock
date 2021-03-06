package gr.eurobank.transactions.esbmock.model.loan.dto;

import gr.eurobank.transactions.esbmock.model.Collateral;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ListOfCollateralsResponse {

    private List<Collateral> listOfCollaterals;

    public List<Collateral> getListOfCollaterals() {
        return listOfCollaterals;
    }

    public void setListOfCollaterals(List<Collateral> listOfCollaterals) {
        this.listOfCollaterals = listOfCollaterals;
    }

}
