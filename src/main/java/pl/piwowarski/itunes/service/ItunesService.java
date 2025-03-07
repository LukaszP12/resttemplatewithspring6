package pl.piwowarski.itunes.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.piwowarski.itunes.proxy.ItunesProxy;
import pl.piwowarski.itunes.proxy.ItunesResponse;
import pl.piwowarski.itunes.proxy.ItunesResult;
import pl.piwowarski.songviewer.Song;
import pl.piwowarski.songviewer.SongFetchable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ItunesService implements SongFetchable {

    private final ItunesProxy itunesClient;
    private final ItunesMapper itunesMapper;

    public ItunesService(ItunesProxy itunesClient, ItunesMapper itunesMapper) {
        this.itunesClient = itunesClient;
        this.itunesMapper = itunesMapper;
    }

    @Override
    public List<Song> fetchAllSongs() {
        List<ItunesResult> itunesResults = fetchShawnMendesSongsFromItunes();
        return itunesResults.stream()
                .map(itunesResult -> new Song(itunesResult.trackName()))
                .collect(Collectors.toList());
    }

    private List<ItunesResult> fetchShawnMendesSongsFromItunes() {
        String jsonSongs = itunesClient.makeGetRequest("shawnmendes", 3);
        if (jsonSongs == null) {
            log.error("jsonSongs was null");
            return Collections.emptyList();
        }
        ItunesResponse shawnMendesResponse = itunesMapper.mapJsonToItunesResponse(jsonSongs);
        log.error("ItunesService fetched: " + shawnMendesResponse);
        return shawnMendesResponse.results();
    }

}
