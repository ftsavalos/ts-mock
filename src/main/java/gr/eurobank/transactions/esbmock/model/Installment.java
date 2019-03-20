package gr.eurobank.transactions.esbmock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Installment implements Serializable {

    private static final long serialVersionUID = 8405512082520889233L;

    private BigDecimal amount;
    private Integer proofNumber;
}
