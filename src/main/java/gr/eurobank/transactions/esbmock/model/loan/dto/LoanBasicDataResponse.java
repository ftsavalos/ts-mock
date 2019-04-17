package gr.eurobank.transactions.esbmock.model.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class LoanBasicDataResponse {

    private BigDecimal totalDebt;
    private BigDecimal totalUnpaidAmount;
    private BigDecimal unpaidCapital;
}