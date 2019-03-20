package gr.eurobank.transactions.esbmock.model.loan.dto;

import gr.eurobank.transactions.esbmock.model.LoanGuaranteeExpense;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class LoanExpenseDetailsResponse {
    private List<LoanGuaranteeExpense> loanExpenseDetails;
}
