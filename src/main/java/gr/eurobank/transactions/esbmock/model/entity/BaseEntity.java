package gr.eurobank.transactions.esbmock.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date created;

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

}
