package gr.eurobank.transactions.esbmock.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Error {

    private String code;
    private String description;

}
