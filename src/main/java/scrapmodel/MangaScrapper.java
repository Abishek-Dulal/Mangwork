package scrapmodel;

import java.io.IOException;
import java.util.Map;

public interface MangaScrapper {
    Map  getMangaData(Map properties) throws IOException, QueryException;
}
