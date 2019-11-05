package scrapmodel.batotomodel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.nodes.Document;
import scrapmodel.AbstractMangaScrapper;
import scrapmodel.QueryException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BatoLoadChapter  extends AbstractMangaScrapper {


    private final String sitename ="https://bato.to";

    @Override
    protected String getMangaQuery(Map properties) throws QueryException {
        String  query = (String) properties.get("latest-chapter-link");
        return sitename+query;
    }

    @Override
    protected Map processPage(Document document)  {
      String elementdata= document.getElementsByTag("script").stream().filter(i->{
            return  i.attr("src").isEmpty();
        }).collect(Collectors.toList()).get(1).data();

        Map resultMap = new HashMap();

       String[] varStrings = elementdata.split(";");
       ObjectMapper mapper = new ObjectMapper();
        for(int i= 0;i<varStrings.length-1;i++){
            String[] varResults = varStrings[i].split("=");


            String res = varResults[1];
             switch (varResults[0].trim()){
                 case  "var chapterId":
                     resultMap.put("chapterid",res.trim());
                     break;
                 case "var nextCha":
                     if(res.equals("null") ) {
                         try {
                             Map nextchapMap = mapper.readValue(res,Map.class);
                             resultMap.put("nextChap",((Map)nextchapMap.get("base")).get("uniqueId"));
                             break;
                         } catch (JsonProcessingException e) {
                             e.printStackTrace();
                         }

                     }
                        resultMap.put("nextChap", null);
                     break;
                 case"var pages":
                     resultMap.put("pages",res);
                     break;
                 case "var images":
                     try {
                         resultMap.put("images",mapper.readValue(res,Map.class));
                     } catch (JsonProcessingException e) {
                         e.printStackTrace();
                     }
                  break;
                 case "var prevCha":
                     if(res.equals("null")) {
                         try {
                             Map prechapMap = mapper.readValue(res,Map.class);
                             resultMap.put("prevChap",((Map)prechapMap.get("base")).get("uniqueId"));
                             break;
                         } catch (JsonProcessingException e) {
                             e.printStackTrace();
                         }
                     }
                     break;
                 case "var seriesId":
                      resultMap.put("seriesId",res);
                      break;
             }
        }


        return resultMap;
    }

}
