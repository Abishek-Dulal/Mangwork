package scrapmodel.query;

import java.util.Map;

public class BatotoQuery implements  QueryCreator {

    private final String sitename ="https://bato.to";

    @Override
    public String searchByAuthorOrBookname(Map<String,String> property) throws QueryException {
        if( property.get("page")==null && property.get("author")==null && property.get("bookname")==null ){
            throw  new QueryException(" :: Author or bookname required");
        }
        String pageNumber = nullCheck(property,"p","page");
        String author = nullCheck(property,"a","author");
        String manganame=nullCheck(property,"q","bookname");
        String search =  sitename +"/" +"search?"+ manganame+"&"+author+"&"+pageNumber;
        return search;
    }


}
