package pl.piwowarski.sampleshawnmendes.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import pl.piwowarski.sampleshawnmendes.proxy.SampleServerShawnMendesResponse;

import static java.util.Collections.emptyList;

@Component
@Log4j2
public class ShawnMendesServiceMapper {

    private final ObjectMapper objectMapper;

    public ShawnMendesServiceMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    SampleServerShawnMendesResponse mapJsonToSampleShawnMendesResponse(String json) throws JsonProcessingException {
        try {
            return objectMapper.readValue(json, SampleServerShawnMendesResponse.class);
        } catch (JsonProcessingException e) {
            log.error("ShawnMendesServiceMapper could not map json");
            return new SampleServerShawnMendesResponse("", emptyList());
        }
    }
}
