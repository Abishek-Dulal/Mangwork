package scrapmodel;

import scrapmodel.batotomodel.BatotoSitesFactory;

public class MangaFetcherFactory  {

    public static InterfaceSitesFactory  getMangasite(String sitename){
        switch (sitename){
            case "bato":
                return  new BatotoSitesFactory();

        }
        return null;
    }

}
