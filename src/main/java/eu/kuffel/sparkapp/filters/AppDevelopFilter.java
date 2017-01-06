package eu.kuffel.sparkapp.filters;

import spark.Filter;
import spark.Request;
import spark.Response;

/**
 * Created by adam on 06.01.17.
 */
public class AppDevelopFilter implements Filter {

    private static AppDevelopFilter instance;

    private AppDevelopFilter(){}

    public static AppDevelopFilter create(){
        if(instance == null){
            instance = new AppDevelopFilter();
        }
        return instance;
    }


    @Override
    public void handle(Request request, Response response) throws Exception {
        System.out.println("AppDevelopFilter.handle");
    }

}
