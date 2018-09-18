package gr.eurobank.transactions.esbmock.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class LoanGuaranteeExpense {

    private String expensesBalance;
    private String paidExpenseFlag;
    private String debitAccount;
    private Date registrationDate;
    private String controlLawyerDispFlag;
    private String cardLoanFlag;
    private String expenseCode;
    private String expenseSequence;
    private String expenseAmount;
    private String overdueAmount;
    private String expenseDescription;
    private String expenseRecupFlagDescription;
    private String currency;
    private String expenseRecupFlag;
    private String efteAmount;
    private String contract;

    private boolean discharged;
}