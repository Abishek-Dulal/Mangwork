package scrapmodel.sites;

import scrapmodel.MangaSites;

public class SiteFactory {

    public static ScrapSite  getSite(MangaSites site){
        switch (site){
            case BATO:
                return  new BatotoSite() ;

        }
        return  null;
    }


}
