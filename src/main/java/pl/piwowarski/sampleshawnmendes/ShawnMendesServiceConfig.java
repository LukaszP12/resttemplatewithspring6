package pl.piwowarski.sampleshawnmendes;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.piwowarski.sampleshawnmendes.proxy.SampleShawnMendesServerProxy;
import pl.piwowarski.sampleshawnmendes.service.ShawnMendesService;
import pl.piwowarski.sampleshawnmendes.service.ShawnMendesServiceMapper;

@Configuration
public class ShawnMendesServiceConfig {

    @Bean
    SampleShawnMendesServerProxy sampleShawnMendesServerProxy() {
        return new SampleShawnMendesServerProxy(new RestTemplate());
    }

    @Bean
    ShawnMendesServiceMapper shawnMendesServiceMapper() {
        return new ShawnMendesServiceMapper(new ObjectMapper());
    }

    @Bean
    ShawnMendesService shawnMendesService() {
        return new ShawnMendesService(
                sampleShawnMendesServerProxy(),
                shawnMendesServiceMapper()
        );
    }
}
