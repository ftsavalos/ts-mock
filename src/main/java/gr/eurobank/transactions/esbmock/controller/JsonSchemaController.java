package gr.eurobank.transactions.esbmock.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;
import gr.eurobank.transactions.esbmock.model.loan.dto.LoanAccountPropertiesResponse;
import gr.eurobank.transactions.esbmock.model.loan.dto.LoanAccountResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.shell.Bootstrap;
import org.springframework.shell.core.JLineShellComponent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class JsonSchemaController {

    private final ObjectMapper mapper;
//    private final JLineShellComponent shellComponent;

    public JsonSchemaController(ObjectMapper mapper) {
        this.mapper = mapper;

    }

    @GetMapping(value = "schemaDeprecated", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getSchemaDeprecated() throws IOException {
        return ResponseEntity.ok(this.getJsonSchemaDeprecated(LoanAccountPropertiesResponse.class));
    }

    @GetMapping(value = "schema", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getSchema() throws IOException {
        return ResponseEntity.ok(this.getJsonSchema(LoanAccountPropertiesResponse.class));
    }

    @GetMapping(value = "schemaFromJson", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getSchemaFromJsonEndpoint() throws JsonProcessingException {
        return ResponseEntity.ok(this.getSchemaFromJson());
    }

    private String getJsonSchemaDeprecated (Class clazz) throws IOException {
        com.fasterxml.jackson.databind.jsonschema.JsonSchema schema = mapper.generateJsonSchema(LoanAccountResponse.class);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(schema);
    }

    private String getJsonSchema(Class clazz) throws JsonProcessingException {
        JsonSchemaGenerator schemaGenerator = new JsonSchemaGenerator(mapper);
        JsonSchema schema = schemaGenerator.generateSchema(clazz);
        return mapper.writeValueAsString(schema);
    }

    private String getSchemaFromJson() throws JsonProcessingException {
        JLineShellComponent shell = new Bootstrap().getJLineShellComponent();

        Object commandResult = shell.executeCommand("! pwd").getResult();
        String jsonOutput = mapper.writeValueAsString(commandResult);
        return jsonOutput;
    }
}
