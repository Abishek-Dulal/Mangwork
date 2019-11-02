package scrapmodel.batotomodel;

import scrapmodel.InterfaceSitesFactory;
import scrapmodel.MangaScrapper;

public class BatotoSitesFactory implements InterfaceSitesFactory {



    @Override
    public MangaScrapper getSearchByAuthorOrBookName() {
        return new BatoSearchByAuthorByBookname();
    }

}
