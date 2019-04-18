package gr.eurobank.transactions.esbmock.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
public class SpecialPricing {

    private String pricingPolicyCode;
    private Double percentage;
    private BigDecimal minimumAmount;
    private Date startDate;
    private Date endDate;
    private String description;
}
