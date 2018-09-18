package gr.eurobank.transactions.esbmock.services;

import gr.eurobank.transactions.esbmock.models.dto.SuccessResponse;
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

    public ResponseEntity<?> getResponseData(Class<?> clazz) {
        return ResponseEntity.accepted().body(this.getObject(clazz));
    }

    public ResponseEntity<SuccessResponse> getSuccessResponse() {
        return ResponseEntity.accepted().body(new SuccessResponse(true));
    }

    public ResponseEntity<?> getResponseObject(Object data) {
        return ResponseEntity.accepted().body(data);
    }

    public <T> T getObject(Class<T> clazz) {
        return this.randomizer().nextObject(clazz);
    }
}
