package scrapmodel;

public class QueryException extends  Exception {

    public QueryException(String message) {
        super("Query Error : invalid Query" + message);
    }
}
