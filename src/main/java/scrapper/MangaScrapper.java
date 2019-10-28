package scrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import scrapmodel.SiteMethods;
import scrapmodel.query.QueryFactory;
import scrapmodel.sites.SiteFactory;

import java.io.IOException;
import java.util.Map;

public class MangaScrapper  {

      private Document scrapPage(String query) throws IOException {
             Document doc  =Jsoup.connect(query).get();
             return doc;
      }

      public Map scrapPage(String site , Map properties, SiteMethods method) throws IOException {
         PageProcessor processor = new PageProcessor();
         Document document = scrapPage(QueryFactory.getSiteQuery(site).get().searchByAuthorOrBookname(properties));
          return  processor.processPage(SiteFactory.getSite(site).get(),document,method);
      }


}
