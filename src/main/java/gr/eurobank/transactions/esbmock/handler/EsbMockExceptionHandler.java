package gr.eurobank.transactions.esbmock.handler;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class EsbMockExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {PathVariableNullityException.class})
    public ResponseEntity handlePathVariableNullityException(PathVariableNullityException ex, WebRequest request) {
        Error error = new Error("SM", ex.getMessage());
        ErrorPayload errorPaylod = new ErrorPayload(Lists.newArrayList(error));
        return ResponseEntity.badRequest().body(errorPaylod);
    }

}
