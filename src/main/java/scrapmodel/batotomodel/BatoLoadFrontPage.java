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

public class BatoLoadFrontPage extends AbstractMangaScrapper {


    private final String sitename ="https://bato.to";

    @Override
    protected String getMangaQuery(Map Properties) throws QueryException {
        return sitename;
    }

    @Override
    protected Map processPage(Document document) {
        Element serieslist_container = document.getElementsByClass("series-browse").get(0);
        Elements serieslist =serieslist_container.getElementsByClass("row").get(0).children();
        Map<String, List<Object>> contlist  = new HashMap<>();
        List<Object> result = new ArrayList<>();
        for(Element element :serieslist){
            Map<String, String> seriesval = new HashMap<>();
            String title =element.getElementsByClass("item-title").get(0).text();
            String chap = element.getElementsByClass("item-volch").get(0).text();
            String chaplink = element.getElementsByClass("item-volch").get(0).getElementsByTag("a").attr("href").split("/")[2];
            String seriesLink = element.getElementsByClass("item-cover").get(0).attr("href");
            String thumbimage = "https:" + element.getElementsByClass("item-cover").get(0).getElementsByTag("img").attr("src");
            seriesval.put("title",title);
            seriesval.put("latest-chapter",chap);
            seriesval.put("series-link",seriesLink.substring(8));
            seriesval.put("thumb-image",thumbimage);
            seriesval.put("latest-chapter-link",chaplink);
            result.add(seriesval);
        }
        contlist.put("result",result);
        List<Object> downloadlist = new ArrayList<>();
        downloadlist.add("thumb-image");
        contlist.put("downloadable",downloadlist);
        return contlist;
     }

}
