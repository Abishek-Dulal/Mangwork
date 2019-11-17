package scrapmodel;

public interface InterfaceSitesFactory {

     MangaScrapper getSearchByAuthorOrBookName();
     MangaScrapper getChapterload();
     MangaScrapper getSeriesload();
     MangaScrapper getLoadFrontPage();



}
