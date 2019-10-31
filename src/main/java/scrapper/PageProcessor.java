package scrapper;

import org.jsoup.nodes.Document;
import scrapmodel.MangaSites;
import scrapmodel.SiteMethods;
import scrapmodel.query.QueryException;
import scrapmodel.query.QueryFactory;
import scrapmodel.sites.ScrapSite;
import scrapmodel.sites.SiteFactory;

import java.io.IOException;
import java.util.*;

public class PageProcessor {



    public Map processPage( MangaSites site,  SiteMethods method ,Map<String,String> properties) throws IOException, QueryException {
        switch(method){
            case SEARCHBYAUTHORORBOOKNAME:
                  String query = QueryFactory.getSiteQuery(site).searchByAuthorOrBookname(properties);
                  Document document= MangaScrapper.scrapPage(query);
                  return  SiteFactory.getSite(site).searchByAuthorOrBookname(document);

            case SEARCHBYGENRE:

                return  null;

        }
        return  null;
    }






}
