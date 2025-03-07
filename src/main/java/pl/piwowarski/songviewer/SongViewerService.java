package pl.piwowarski.songviewer;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class SongViewerService {

    private final List<SongFetchable> services;

    public SongViewerService(List<SongFetchable> services) {
        this.services = services;
    }

    public List<Song> viewAllSongs() throws JsonProcessingException {
        List<Song> songsToView = new ArrayList<>();
        services.forEach(
                service -> {
                    try {
                        songsToView.addAll(service.fetchAllSongs());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        return songsToView;
    }
}
