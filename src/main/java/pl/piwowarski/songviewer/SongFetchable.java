package pl.piwowarski.songviewer;

import java.io.IOException;
import java.util.List;

public interface SongFetchable {

    List<Song> fetchAllSongs() throws IOException;

}
