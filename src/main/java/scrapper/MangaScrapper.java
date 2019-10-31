package scrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import scrapmodel.SiteMethods;
import scrapmodel.query.QueryFactory;
import scrapmodel.MangaSites;
import scrapmodel.sites.SiteFactory;

import java.io.IOException;
import java.util.Map;

public class MangaScrapper  {

      public static Document scrapPage(String query) throws IOException {
             Document doc  =Jsoup.connect(query).get();
             return doc;
      }


}
