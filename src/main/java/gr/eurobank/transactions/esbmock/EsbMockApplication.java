package gr.eurobank.transactions.esbmock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@SpringBootApplication
public class EsbMockApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(EsbMockApplication.class, args);
//        Bootstrap bootstrap = new Bootstrap();
//        JLineShellComponent shell = bootstrap.getJLineShellComponent();
//        shell.executeCommand("! ./src/main/resources/json-schema-tool/schema-guru-0.6.2 schema ./src/main/resources/json-schema-tool/json.json");
//        Process exec = Runtime.getRuntime().exec(" pwd");
//
//        System.out.println(exec.getOutputStream());
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

//    @Bean
//    public JLineShellComponent shellComponent() {
//        return new Bootstrap().getJLineShellComponent();
//    }
}