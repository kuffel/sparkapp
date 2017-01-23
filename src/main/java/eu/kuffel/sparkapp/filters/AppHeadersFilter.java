package eu.kuffel.sparkapp.filters;

import eu.kuffel.sparkapp.Application;
import spark.Filter;
import spark.Request;
import spark.Response;

/**
 * This class adds default HTTP headers. All request prefixed with api will be sent as json.
 *
 * @author kuffel
 * @version 06.01.2017
 */
public class AppHeadersFilter implements Filter {

    private static AppHeadersFilter instance;

    private AppHeadersFilter(){}

    public static AppHeadersFilter create(){
        if(instance == null){
            instance = new AppHeadersFilter();
        }
        return instance;
    }


    @Override
    public void handle(Request request, Response response) throws Exception {
        if(request.uri().startsWith("/api")){
            response.type("application/json");
        }
        response.header("Server", Application.APP_NAME);
    }

}
