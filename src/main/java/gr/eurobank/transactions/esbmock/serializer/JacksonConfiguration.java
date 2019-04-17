package gr.eurobank.transactions.esbmock.serializer;


import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class JacksonConfiguration {

    @Bean
    public ObjectMapper jsonObjectMapper() {

        SimpleModule module = new SimpleModule();
        module.addSerializer(Date.class, new JsonDateSerializer());
        module.addDeserializer(Date.class, new JsonDateDeserializer());

        ObjectMapper mapper = new ObjectMapper();
        mapper.getFactory().enable(Feature.ALLOW_COMMENTS);
        mapper.registerModule(module);

        return mapper;
    }

}
