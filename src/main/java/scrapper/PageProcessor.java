package scrapper;

import org.jsoup.nodes.Document;
import scrapmodel.SiteMethods;
import scrapmodel.sites.ScrapSite;
import java.util.*;

public class PageProcessor {

  public Map processPage(ScrapSite site, Document document, SiteMethods method){
       switch(method){
           case SEARCHBYAUTHORORBOOKNAME:
               return  site.searchByAuthorOrBookname(document);
           case SEARCHBYGENRE:
               return  site.searchByGenres(document);

       }
       return  null;
  }






}
