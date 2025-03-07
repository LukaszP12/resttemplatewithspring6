package pl.piwowarski.fromfilesongreader;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import pl.piwowarski.songviewer.Song;
import pl.piwowarski.songviewer.SongFetchable;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class FromFileSongReaderService implements SongFetchable {

    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;

    FromFileSongReaderService(ResourceLoader resourceLoader, ObjectMapper objectMapper) {
        this.resourceLoader = resourceLoader;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Song> fetchAllSongs() {
        Resource resource = resourceLoader.getResource("classpath:songs.json");
        try {
            File file = resource.getFile();
            FileSongWrapper fileSongWrapper = objectMapper.readValue(file, FileSongWrapper.class);
            return fileSongWrapper.songs()
                    .stream()
                    .map(fileSong -> new Song(fileSong.trackName()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.error("songs.json error. " + e.getMessage());
        }
        return null;
    }

}
