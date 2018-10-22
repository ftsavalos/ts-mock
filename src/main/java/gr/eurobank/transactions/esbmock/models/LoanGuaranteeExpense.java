package gr.eurobank.transactions.esbmock.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@Data
public class LoanGuaranteeExpense {

    private BigDecimal expensesBalance;
    private String paidExpenseFlag;
    private String debitAccount;
    private Date registrationDate;
    private String controlLawyerDispFlag;
    private String cardLoanFlag;
    private String expenseCode;
    private String expenseSequence;
    private BigDecimal expenseAmount;
    private BigDecimal overdueAmount;
    private String expenseDescription;
    private String expenseRecupFlagDescription;
    private String currency;
    private String expenseRecupFlag;
    private BigDecimal efteAmount;
    private String contract;

    private boolean discharged;
}