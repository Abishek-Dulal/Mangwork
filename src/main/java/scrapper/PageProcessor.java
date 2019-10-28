package scrapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.nodes.Document;
import scrapmodel.BatotoSite;

import java.util.*;

/*
    {
      serieslist:{
        "type" :"id"
        "dom_search_string":"series-list"
        "contlist-prop":"iterate ref"
        "contlist":{
            "type" : "class"
            "dom_search_string":"col-24 item hairlines-bottom is-hot no-flag"
            "result":[{
               "title" :{
                 "type" :"id"
                 "dom_search_string":"item-title"

                }
            }]
          }
      }


    }

*/


public class PageProcessor {

    private ObjectMapper objectMapper;

    public PageProcessor() {
        objectMapper= new ObjectMapper();
    }

    public <K,V> Optional<Map> processPage(Document document, String PropertyCriteareaJSON) throws JsonProcessingException {

        Map resultMap = new HashMap();




        new BatotoSite().seachByAuthorOrBookname(document);
        return Optional.ofNullable(resultMap);
    }






}
