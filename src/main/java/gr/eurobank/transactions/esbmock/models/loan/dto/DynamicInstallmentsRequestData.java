package gr.eurobank.transactions.esbmock.models.loan.dto;

import gr.eurobank.transactions.esbmock.models.Installment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DynamicInstallmentsRequestData implements Serializable {

    private static final long serialVersionUID = 1926439655739712011L;

    List<Installment> installments;

}
