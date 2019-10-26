package scrapper;

import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

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

    public <K,V> Optional<HashMap<K,V>> processPage(Document document, HashMap PropertyCritearea){

        process(document,PropertyCritearea,null);

        return Optional.empty();
    }

    private HashMap  process(Document Document,HashMap propertymap,HashMap resultmap){



       return null;
    }


}
