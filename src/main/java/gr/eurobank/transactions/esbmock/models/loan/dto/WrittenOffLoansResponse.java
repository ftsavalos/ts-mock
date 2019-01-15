package gr.eurobank.transactions.esbmock.models.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class WrittenOffLoansResponse {

    private BigDecimal independentInterestOfWriteOffUnpaid;
    private BigDecimal legalExpensesAfterWriteOffUnpaid;
    private BigDecimal totalDebtUnpaid;
}