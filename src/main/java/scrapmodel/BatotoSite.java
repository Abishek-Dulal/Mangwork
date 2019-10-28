package scrapmodel;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BatotoSite implements ScrapSite {
    @Override
    public Map seachByAuthorOrBookname(Document doc) {
        Element serieslist_container = doc.getElementById("series-list");
        Elements serieslist =serieslist_container.getElementsByClass("col-24 item hairlines-bottom is-hot no-flag");
        Map  contlist  = new HashMap();
        List result = new ArrayList();
        for(Element element :serieslist){
            Map seriesval = new HashMap();
            String title =element.getElementsByClass("item-title").get(0).text();
            String chap = element.getElementsByClass("item-volch").get(0).text();
            String seriesLink = element.getElementsByClass("item-cover").get(0).attr("href");
            String thumbimage = element.getElementsByClass("item-cover").get(0).getElementsByTag("img").attr("src");
            seriesval.put("title",title);
            seriesval.put("latest-chapter",chap);
            seriesval.put("series-link",seriesLink);
            seriesval.put("thumb-image",thumbimage);
            result.add(seriesval);
        }
        contlist.put("result",result);
        List downloadlist = new ArrayList();
        downloadlist.add("thumb-image");
        contlist.put("downloadable",downloadlist);
        return contlist;
    }
}
