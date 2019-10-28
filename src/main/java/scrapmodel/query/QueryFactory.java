package scrapmodel.query;

import java.util.Optional;

public class QueryFactory {

    public  static Optional<QueryCreator> getSiteQuery(String site){
        switch (site){
            case "bato":
             return  Optional.of(new BatotoQuery());

        }
        return Optional.ofNullable(null);
    }
}
