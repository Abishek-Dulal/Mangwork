package scrapmodel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;

public  abstract class AbstractMangaScrapper implements MangaScrapper {

    @Override
    public Map getMangaData(Map properties) throws IOException, QueryException {
        return processPage(scrapManga(getMangaQuery(properties)));
    }

    private Document scrapManga(String query) throws IOException {
       return Jsoup.connect(query).get();
    }

    protected abstract String getMangaQuery(Map Properties) throws QueryException;

    protected abstract Map processPage(Document document);

     protected String nullCheck(Map source,String tag,String checkval){
        String  data = (String) source.get(checkval);
         return tag+"=" + (data!=null ? data:"");
    }



}
