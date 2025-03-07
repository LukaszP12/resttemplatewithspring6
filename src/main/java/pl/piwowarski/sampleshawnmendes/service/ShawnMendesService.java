package pl.piwowarski.sampleshawnmendes.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.piwowarski.sampleshawnmendes.proxy.SampleServerShawnMendesResponse;
import pl.piwowarski.sampleshawnmendes.proxy.SampleShawnMendesServerProxy;
import pl.piwowarski.songviewer.Song;
import pl.piwowarski.songviewer.SongFetchable;

import java.util.List;

@Service(value = "songFetchable")
@Log4j2
public class ShawnMendesService implements SongFetchable {

    private final SampleShawnMendesServerProxy sampleShawnMendesServerClient;
    private final ShawnMendesServiceMapper shawnMendesServiceMapper;

    public ShawnMendesService(SampleShawnMendesServerProxy sampleShawnMendesServerClient, ShawnMendesServiceMapper shawnMendesServiceMapper) {
        this.sampleShawnMendesServerClient = sampleShawnMendesServerClient;
        this.shawnMendesServiceMapper = shawnMendesServiceMapper;
    }

    @Override
    public List<Song> fetchAllSongs() throws JsonProcessingException {
        String songs = fetchAllShawnMendesSongsFromLocalhost();
        return List.of(new Song(songs));
    }

    public String fetchAllShawnMendesSongsFromLocalhost() throws JsonProcessingException {
        String jsonSongs = sampleShawnMendesServerClient.makeGetRequest();
        if (jsonSongs == null) {
            log.error("jsonSongs was null");
            return "";
        }
        SampleServerShawnMendesResponse sampleServerShawnMendesResponse = shawnMendesServiceMapper.mapJsonToSampleShawnMendesResponse(jsonSongs);
        log.info("ShawnMendesService fetched: " + sampleServerShawnMendesResponse);
        return sampleServerShawnMendesResponse.message();
    }

//    public void testClient() throws JsonProcessingException {
//        String postRequest = sampleShawnMendesServerClient.makePostRequest();
//        if (postRequest != null) {
//            SampleServerShawnMendesResponse sampleServerShawnMendesResponse = shawnMendesServiceMapper.mapJsonToSampleShawnMendesResponse(postRequest);
//            log.error(sampleServerShawnMendesResponse);
//        }
//        String getJsonSampleShawnMendesServer = sampleShawnMendesServerClient.makeGetRequest();
//        if (getJsonSampleShawnMendesServer != null) {
//            SampleServerShawnMendesResponse sampleServerShawnMendesResponse = shawnMendesServiceMapper.mapJsonToSampleShawnMendesResponse(getJsonSampleShawnMendesServer);
//            log.error(sampleServerShawnMendesResponse);
//        }
//        sampleShawnMendesServerClient.makeDeleteRequest(0);
//        String getJsonSampleShawnMendesServer2 = sampleShawnMendesServerClient.makeGetRequest();
//        if (getJsonSampleShawnMendesServer2 != null) {
//            SampleServerShawnMendesResponse sampleServerShawnMendesResponse = shawnMendesServiceMapper.mapJsonToSampleShawnMendesResponse(getJsonSampleShawnMendesServer2);
//            log.error(sampleServerShawnMendesResponse);
//        }
//    }
}
