package gr.eurobank.transactions.esbmock;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.MessageFormat;

@SpringBootApplication
public class EsbMockApplication {

    @Value("${endpoint.esb.host}")
    private String esbSwaggerEndpoint;

    public static void main(String[] args) throws IOException {
        SpringApplication.run(EsbMockApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public String esbSwaggerApiDocJson(RestTemplate restTemplate) {
        return restTemplate.exchange(MessageFormat.format(esbSwaggerEndpoint, "api-doc"), HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, (Object) null).getBody();
    }
}