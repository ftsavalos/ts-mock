package gr.eurobank.transactions.esbmock;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String requestId;

    public History() {}

    public History(String requestId) {
        this.requestId = requestId;
    }
}