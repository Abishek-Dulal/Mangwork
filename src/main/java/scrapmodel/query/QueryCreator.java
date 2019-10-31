package scrapmodel.query;

import java.util.Map;

public interface QueryCreator {
    String  searchByAuthorOrBookname(Map<String,String> property) throws QueryException;

    default  String nullCheck(Map source,String tag,String checkval){
        String  data = (String) source.get(checkval);
        String dataString = tag+"=" + (data!=null ? data:"");
        return dataString;
    }

}
