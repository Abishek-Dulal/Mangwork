package scrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;

public class MangaScrapper  {

//    public <K,V> HashMap<K,V> getScarappedContent(String ContentSite){
//
//        return null;
//    }

      public Document scrapPage(String query) throws IOException {
             Document doc  =Jsoup.connect(query).get();
             return doc;
      }


}
