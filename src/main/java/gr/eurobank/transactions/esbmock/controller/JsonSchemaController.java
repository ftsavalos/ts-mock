package gr.eurobank.transactions.esbmock.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;
import gr.eurobank.transactions.esbmock.model.loan.dto.AccountBalanceResponse;
import gr.eurobank.transactions.esbmock.model.loan.dto.LoanAccountPropertiesResponse;
import gr.eurobank.transactions.esbmock.model.loan.dto.LoanAccountResponse;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;

@RestController
public class JsonSchemaController {

    private final ObjectMapper mapper;

    public JsonSchemaController(ObjectMapper mapper) {
        this.mapper = mapper;

    }

    @GetMapping(value = "schemaDeprecated", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getSchemaDeprecated() throws IOException {
        return ResponseEntity.ok(this.getJsonSchemaDeprecated(LoanAccountPropertiesResponse.class));
    }

    @GetMapping(value = "schema", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getSchema() throws IOException {
        return ResponseEntity.ok(this.getJsonSchema(AccountBalanceResponse.class));
    }

    @PostMapping(value = "schemaFromJson", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getSchemaFromJsonEndpoint(@RequestBody String json) throws IOException {
        return ResponseEntity.ok(this.getSchemaFromJsonRuntime(json));
    }

    private String getJsonSchemaDeprecated(Class clazz) throws IOException {
        com.fasterxml.jackson.databind.jsonschema.JsonSchema schema = mapper.generateJsonSchema(LoanAccountResponse.class);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(schema);
    }

    private String getJsonSchema(Class clazz) throws JsonProcessingException {
        JsonSchemaGenerator schemaGenerator = new JsonSchemaGenerator(mapper);
        JsonSchema schema = schemaGenerator.generateSchema(clazz);
        return mapper.writeValueAsString(schema);
    }

    private String getSchemaFromJsonRuntime(String json) throws IOException {
        File tempFile = new File("./src/main/resources/json-schema-tool/json_temp.json");
        FileUtils.writeStringToFile(tempFile, json);
        String command = "./src/main/resources/json-schema-tool/schema-guru-0.6.2 schema  --no-length ./src/main/resources/json-schema-tool/json_temp.json";

        File root = new File(".");
        Process p = Runtime.getRuntime().exec(command, (String[]) null, root);
        Reader input = new InputStreamReader(p.getInputStream());
        Iterator i$ = IOUtils.readLines(input).iterator();
        StringBuilder stringBuilder = new StringBuilder();

        String line;
        while (i$.hasNext()) {
            line = (String) i$.next();
            stringBuilder.append(line);
        }
        FileUtils.forceDelete(tempFile);

        return stringBuilder.toString();
    }
}
