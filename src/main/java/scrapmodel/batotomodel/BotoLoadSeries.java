package scrapmodel.batotomodel;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scrapmodel.AbstractMangaScrapper;
import scrapmodel.QueryException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BotoLoadSeries extends AbstractMangaScrapper{


    private final String sitename ="https://bato.to";

    @Override
    protected String getMangaQuery(Map properties) throws QueryException {
         String series = (String) properties.get("series");
        return sitename +  "/series/"+series ;
    }

    @Override
    protected Map processPage(Document document) {
        Map resultMap = new HashMap();
         resultMap.put("text",document.getElementsByTag("pre").get(0).text());

         Elements chapters=document.getElementsByClass("chapter-list").get(0)
                           .getElementsByClass("main").get(0).children();

          Element ser=document.getElementById("series-page").getElementsByClass("item-title").get(0);
           resultMap.put("series-name",ser.text());
           resultMap.put("series",ser.child(0).attr("href").substring(8));

         List chaplist = new ArrayList();
         for( Element chapter: chapters){
             Map chap = new HashMap();
             chap.put("chaptext",chapter.getElementsByClass("chapt").text());
             chap.put("chaplink",chapter.getElementsByClass("chapt").attr("href").substring(9));

             chaplist.add(chap);
         }
         resultMap.put("chapters",chaplist);

         return resultMap;
    }
}
