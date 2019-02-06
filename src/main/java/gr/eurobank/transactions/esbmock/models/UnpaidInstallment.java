package gr.eurobank.transactions.esbmock.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
public class UnpaidInstallment {

    private Date dateOfRequest;
    private BigDecimal overdueInterest;
    private BigDecimal capital;
    private BigDecimal convetionalInterest;
    private BigDecimal totalDebt;
}
