package gr.eurobank.transactions.esbmock.model.loan.dto;

import gr.eurobank.transactions.esbmock.model.SpecialPricing;
import lombok.Data;

import java.util.List;

@Data
public class SpecialPricingsResponse {

    List<SpecialPricing> pricingPolicies;

}
