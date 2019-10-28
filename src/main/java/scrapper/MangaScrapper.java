package scrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;

public class MangaScrapper  {

      public Document scrapPage(String query) throws IOException {
             Document doc  =Jsoup.connect(query).get();
             return doc;
      }


}
