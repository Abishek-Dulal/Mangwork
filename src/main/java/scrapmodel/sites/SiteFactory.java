package scrapmodel.sites;

import java.util.Optional;

public class SiteFactory {

    public static Optional<ScrapSite>  getSite(String site){
        switch (site){
            case "bato":
                return  Optional.of(new BatotoSite() );

        }
        return  Optional.ofNullable(null);
    }


}
