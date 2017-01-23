package eu.kuffel.sparkapp;

import eu.kuffel.sparkapp.filters.AppDevelopFilter;
import eu.kuffel.sparkapp.filters.AppHeadersFilter;
import eu.kuffel.sparkapp.routes.AppDevelopRoute;
import spark.route.RouteOverview;

import static spark.Spark.*;

/**
 * This class is the main entry point for your microservice.
 * All endpoints are defined in this main method.
 *
 * @author kuffel
 * @version 06.01.2017
 */
public class Application {

    public static final String SERVER_HEADER = "sparkapp";




    public static void main(String[] args) {

        /*
        if (localhost) {
            String projectDir = System.getProperty("user.dir");
            String staticDir = "/src/main/resources/webroot";
            staticFiles.externalLocation(projectDir + staticDir);
        } else {
            staticFiles.location("/webroot");
        }
        */
        staticFiles.location("/webroot");
        staticFiles.expireTime(600);

        int maxThreads = 8;
        int minThreads = 2;
        int timeOutMillis = 30000;
        threadPool(maxThreads, minThreads, timeOutMillis);
        port(9999);

        RouteOverview.enableRouteOverview("/develop/routes");

        // Filter mapping

        before("*", "[*/*]", AppHeadersFilter.create());




        //before("/api/*", AppDevelopFilter.create());

        // Route mapping

        get( "/api/develop", "[*/*]", AppDevelopRoute.create());
        post( "/api/develop", "[*/*]", AppDevelopRoute.create());
        patch( "/api/develop", "[*/*]", AppDevelopRoute.create());
        put( "/api/develop", "[*/*]", AppDevelopRoute.create());
        head( "/api/develop", "[*/*]", AppDevelopRoute.create());
        options( "/api/develop", "[*/*]", AppDevelopRoute.create());
        delete( "/api/develop", "[*/*]", AppDevelopRoute.create());


        //exception(Exception.class, AppDevelopRoute.create());
        notFound(AppDevelopRoute.create());

        /*
        exception(Exception.class, (exception, request, response) -> {
            // Handle the exception here
        });
        */

        /*
        post("/yourUploadPath", (request, response) -> {
            request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
            try (InputStream is = request.raw().getPart("uploaded_file").getInputStream()) {
                // Use the input stream to create a file
            }
            return "File uploaded";
        });
        */


        get("/hello", (req, res) -> "Hello World");

    }

}
