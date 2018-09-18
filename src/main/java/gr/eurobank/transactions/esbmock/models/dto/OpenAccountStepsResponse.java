package gr.eurobank.transactions.esbmock.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OpenAccountStepsResponse {
    private String completedTasks;
    private String productCode;
    private String subproductDescription;
    private String subproductCode;
    private String taskList;
    private String productType;
    private String productDescription;
    private String requiredTasks;
}
