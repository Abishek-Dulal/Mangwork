package scrapmodel.query;

import scrapmodel.MangaSites;

public class QueryFactory {

    public  static QueryCreator getSiteQuery(MangaSites site){
        switch (site){
            case BATO:
             return  new BatotoQuery();

        }
        return null;
    }
}
