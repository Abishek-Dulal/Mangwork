package scrapmodel.sites;


import org.jsoup.nodes.Document;

import java.util.Map;

public interface ScrapSite {
     Map searchByAuthorOrBookname(Document document);
     Map searchByGenres(Document document);

}
