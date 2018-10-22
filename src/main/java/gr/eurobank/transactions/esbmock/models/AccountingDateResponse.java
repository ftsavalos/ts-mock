package gr.eurobank.transactions.esbmock.models;

import lombok.Data;

import java.util.Date;

@Data
public class AccountingDateResponse {

    private Date currentAccountingDate;
    private Date nextAccountingDate;
}
