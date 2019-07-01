package gr.eurobank.transactions.esbmock.model.loan.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class GetFlexiResponse {

    private Date startDate;
    private BigDecimal installmentAmount;
}

