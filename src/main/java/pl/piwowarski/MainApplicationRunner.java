package pl.piwowarski;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import pl.piwowarski.songviewer.Song;
import pl.piwowarski.songviewer.SongViewerService;

import java.util.List;

@Component
@Log4j2
public class MainApplicationRunner {

    private final SongViewerService songViewerService;

    public MainApplicationRunner(SongViewerService songViewerService) {
        this.songViewerService = songViewerService;
    }

    public void run() throws JsonProcessingException {
        List<Song> songs = songViewerService.viewAllSongs();
        log.info(songs);
//        itunesService.fetchShawnMendesSongsFromItunes();
//        shawnMendesService.testClient();
    }
}
