package gr.eurobank.transactions.esbmock.services;

import gr.eurobank.transactions.esbmock.models.SuccessResponse;
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
        return ResponseEntity.ok().body(new SuccessResponse(true, "test"));
    }

    public ResponseEntity<?> convertObjectToResponseEntity(Object data) {
        return ResponseEntity.ok().body(data);
    }

    public <T> T getObject(Class<T> clazz) {
        return this.randomizer().nextObject(clazz);
    }
}
