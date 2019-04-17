package gr.eurobank.transactions.esbmock.service;

import com.google.common.collect.Lists;
import gr.eurobank.transactions.esbmock.handler.Error;
import gr.eurobank.transactions.esbmock.handler.ErrorPayload;
import gr.eurobank.transactions.esbmock.model.SuccessResponse;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EsbMockService {

    private EnhancedRandom randomizer() {
        return EnhancedRandomBuilder
                .aNewEnhancedRandomBuilder()
                .minCollectionSize(2)
                .maxCollectionSize(5)
                .maxStringLength(10)
                .build();
    }

    public ResponseEntity<?> getResponseEntityByClass(Class<?> clazz) {
        return ResponseEntity.ok().body(this.getObject(clazz));
    }

    public ResponseEntity<SuccessResponse> getSuccessResponse() {
        return ResponseEntity.ok().body(new SuccessResponse(true));
    }

    public ResponseEntity<?> convertObjectToResponseEntity(Object data) {
        return ResponseEntity.ok().body(data);
    }

    public <T> T getObject(Class<T> clazz) {
        return this.randomizer().nextObject(clazz);
    }

    public ResponseEntity<ErrorPayload> getErrorResponse(String code, String description) {
        return ResponseEntity.badRequest().body(new ErrorPayload(Lists.newArrayList(new Error(code, description))));
    }
}
