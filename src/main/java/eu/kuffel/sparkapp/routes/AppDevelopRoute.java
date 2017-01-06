package eu.kuffel.sparkapp.routes;

import org.json.JSONObject;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by adam on 06.01.17.
 */
public class AppDevelopRoute implements Route {

    private static AppDevelopRoute instance;

    private AppDevelopRoute(){}

    public static AppDevelopRoute create(){
        if(instance == null){
            instance = new AppDevelopRoute();
        }
        return instance;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        JSONObject responseJSON = new JSONObject();
        return responseJSON;
    }

}
