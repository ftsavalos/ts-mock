package gr.eurobank.transactions.esbmock.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
public class History extends BaseEntity {

    private String requestId;
    private boolean success;
    private boolean uatFlag;
    private String flow;
    @Column(columnDefinition = "TEXT")
    private String inputOriginator;
    @Column(columnDefinition = "TEXT")
    private String processOutput;
    @Column(columnDefinition = "TEXT")
    private String error;
    private String errorOnTransaction;

    public History() {}

    public History(String requestId, boolean success, String flow, String inputOriginator, String processOutput, boolean uatFlag, String error, String errorOnTransaction) {
        this.requestId = requestId;
        this.success = success;
        this.flow = flow;
        this.inputOriginator = inputOriginator;
        this.processOutput = processOutput;
        this.uatFlag = uatFlag;
        this.error = error;
        this.errorOnTransaction = errorOnTransaction;
    }
}