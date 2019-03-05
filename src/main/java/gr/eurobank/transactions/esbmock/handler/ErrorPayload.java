package gr.eurobank.transactions.esbmock.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ErrorPayload {

   List<Error> errors;

}


