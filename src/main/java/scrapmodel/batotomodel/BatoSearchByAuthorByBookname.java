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

public class BatoSearchByAuthorByBookname extends AbstractMangaScrapper {

    private final String sitename ="https://bato.to";

    @Override
    protected String getMangaQuery(Map property) throws QueryException {
        if( property.get("page")==null && property.get("author")==null && property.get("bookname")==null ){
            throw  new QueryException(" :: Author or bookname required");
        }
        String pageNumber = nullCheck(property,"p","page");
        String author = nullCheck(property,"a","author");
        String manganame=nullCheck(property,"q","bookname");
        return sitename +"/" +"search?"+ manganame+"&"+author+"&"+pageNumber;
    }

    //latest-chapter-link
    @Override
    protected Map processPage(Document doc) {
        Element serieslist_container = doc.getElementById("series-list");
        Elements serieslist =serieslist_container.getElementsByClass("col-24 item hairlines-bottom is-hot no-flag");
        Map<String, List<Object>> contlist  = new HashMap<>();
        List<Object> result = new ArrayList<>();
        for(Element element :serieslist){
            Map<String, String> seriesval = new HashMap<>();
            String title =element.getElementsByClass("item-title").get(0).text();
            String chap = element.getElementsByClass("item-volch").get(0).text();
            String chaplink = element.getElementsByClass("item-volch").get(0).getElementsByTag("a").attr("href");
            String seriesLink = element.getElementsByClass("item-cover").get(0).attr("href");
            String thumbimage = "https:" + element.getElementsByClass("item-cover").get(0).getElementsByTag("img").attr("src");
            seriesval.put("title",title);
            seriesval.put("latest-chapter",chap);
            seriesval.put("series-link",seriesLink);
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
