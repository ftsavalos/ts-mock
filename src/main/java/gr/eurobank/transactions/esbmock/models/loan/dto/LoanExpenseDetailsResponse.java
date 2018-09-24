package gr.eurobank.transactions.esbmock.models.loan.dto;

import gr.eurobank.transactions.esbmock.models.LoanGuaranteeExpense;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class LoanExpenseDetailsResponse {
    private List<LoanGuaranteeExpense> loanExpenseDetails;
}
