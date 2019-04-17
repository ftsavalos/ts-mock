package gr.eurobank.transactions.esbmock.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonDateDeserializer extends JsonDeserializer<Date> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonDateDeserializer.class);

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        Date date = null;

        try {
            date = tryParse(jsonParser, "yyyy-MM-dd'T'HH:mm:ss");
        } catch (ParseException e1) {

            try {
                date = tryParse(jsonParser, "yyyy-MM-dd");
            } catch (ParseException e2) {
                LOGGER.error(e2.getMessage(), e2);
            }

        }

        return date;
    }

    /**
     * @param jsonParser
     * @param pattern
     * @return the date parsed
     * @throws IOException
     * @throws ParseException
     */
    private Date tryParse(JsonParser jsonParser, String pattern) throws IOException, ParseException {

        Date date = null;

        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String dateText = jsonParser.getText();

        date = format.parse(dateText);

        return date;
    }
}
