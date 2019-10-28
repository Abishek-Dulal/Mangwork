package scrapmodel;


import org.jsoup.nodes.Document;

import java.util.Map;

public interface ScrapSite {
     Map seachByAuthorOrBookname(Document document);
}
