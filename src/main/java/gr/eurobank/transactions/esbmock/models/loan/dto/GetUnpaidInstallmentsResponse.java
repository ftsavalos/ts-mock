package gr.eurobank.transactions.esbmock.models.loan.dto;


import gr.eurobank.transactions.esbmock.models.UnpaidInstallment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class GetUnpaidInstallmentsResponse {

    private String businessUnit;
    private Date currentDate;
    private List<UnpaidInstallment> unpaidInstallments;

}
