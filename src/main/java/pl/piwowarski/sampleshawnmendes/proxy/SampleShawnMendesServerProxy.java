package pl.piwowarski.sampleshawnmendes.proxy;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Log4j2
@Component
public class SampleShawnMendesServerProxy {

    @Autowired
    private final RestTemplate restTemplate;

    @Value("${sample-shawn-mendes-server.service.url}")
    String url;

    @Value("${sample-shawn-mendes-server.service.port}")
    String port;

    public SampleShawnMendesServerProxy(RestTemplate restTemplate) {
        this.restTemplate = new RestTemplate();
    }

    public String makeGetRequest() {
        UriComponentsBuilder builder = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(url)
                .port(port)
                .path("/shawn/songs");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("requestId", "sasasasasas");
        HttpEntity<SampleShawnMendesRequest> httpEntity = new HttpEntity<>(null, httpHeaders);
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    builder.build().toUri(),
                    HttpMethod.GET,
                    httpEntity,
                    String.class
            );
            return response.getBody();
        } catch (RestClientResponseException exception) {
            log.error(exception.getStatusText() + " " + exception.getStatusCode().value());
        } catch (ResourceAccessException exception) {
            log.error(exception.getMessage());
        } catch (RestClientException exception) {
            log.error(exception.getMessage());
        }
        return null;
    }

    public String makePostRequest() {
        UriComponentsBuilder builder = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(url)
                .port(port)
                .path("/shawn/songs");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("requestId", "sasasasasas");
        SampleShawnMendesRequest requestBody = new SampleShawnMendesRequest("I still haven't found what I am looking for");
        HttpEntity<SampleShawnMendesRequest> httpEntity = new HttpEntity<>(requestBody, httpHeaders);
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    builder.build().toUri(),
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );
            return response.getBody();
        } catch (RestClientResponseException exception) {
            log.error(exception.getStatusText() + " " + exception.getStatusCode().value());
        } catch (ResourceAccessException exception) {
            log.error(exception.getMessage());
        } catch (RestClientException exception) {
            log.error(exception.getMessage());
        }
        return null;
    }

    public String makeDeleteRequest(int id) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(url)
                .port(port)
//                .path("/shawn/songs/" + id)
                .path("/shawn/songs")
                .queryParam("id", id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("requestId", "sasasasasas");
        HttpEntity<SampleShawnMendesRequest> httpEntity = new HttpEntity<>(null, httpHeaders);
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    builder.build().toUri(),
                    HttpMethod.DELETE,
                    httpEntity,
                    String.class
            );
            return response.getBody();
        } catch (RestClientResponseException exception) {
            log.error(exception.getStatusText() + " " + exception.getStatusCode().value());
        } catch (ResourceAccessException exception) {
            log.error(exception.getMessage());
        } catch (RestClientException exception) {
            log.error(exception.getMessage());
        }
        return null;
    }

}
