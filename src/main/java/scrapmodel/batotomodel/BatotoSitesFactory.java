package scrapmodel.batotomodel;

import scrapmodel.InterfaceSitesFactory;
import scrapmodel.MangaScrapper;

public class BatotoSitesFactory implements InterfaceSitesFactory {



    @Override
    public MangaScrapper getSearchByAuthorOrBookName() {
        return new BatoSearchByAuthorByBookname();
    }

    @Override
    public MangaScrapper getChapterload() {
        return new BatoLoadChapter();
    }

    public  MangaScrapper getSeriesload(){
        return  new BotoLoadSeries();
    }

    @Override
    public MangaScrapper getLoadFrontPage() {
        return new BatoLoadFrontPage();
    }


}
