package eu.kuffel.sparkapp;

import com.mongodb.Block;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.async.client.*;
import eu.kuffel.sparkapp.filters.AppDevelopFilter;
import eu.kuffel.sparkapp.filters.AppHeadersFilter;
import eu.kuffel.sparkapp.routes.AppDevelopRoute;
import eu.kuffel.sparkapp.utils.AppRandom;
import org.apache.commons.cli.*;
import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;
import spark.route.RouteOverview;

import java.io.IOException;
import java.nio.file.*;

import static spark.Spark.*;

/**
 * This class is the main entry point for your microservice.
 * All endpoints are defined in this main method.
 *
 * @author kuffel
 * @version 06.01.2017
 */
public class Application {

    /**
     * Default name for this application
     */
    public static final String APP_NAME = "sparkapp";

    /**
     * Version string for this application.
     */
    public static final String APP_VERSION = "0.0.1";

    /**
     *  Used by cli parser to display usage information in console.
     */
    public static final String CMD_DEFAULT = "java -jar "+new java.io.File(Application.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getName();

    /**
     * Optional header to display some information in cli.
     */
    public static final String CMD_HEADER = "";

    /**
     * Optional header to display some information in cli.
     */
    public static final String CMD_FOOTER = "";

    /**
     * Currently active config.
     */
    public static JSONObject config;

    /**
     * Reference to a connected mongo db client.
     */
    public static MongoClient db;



    public static void main(String[] args) {
        CommandLine cmd = parseArgs(args);
        if(config == null){
            config = getDefaultConfig();
        }


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

        int maxThreads = config.getInt("threads_max");
        int minThreads = config.getInt("threads_min");
        int timeOutMillis = config.getInt("threads_idle_timeout_ms");
        threadPool(maxThreads, minThreads, timeOutMillis);
        port(config.getInt("port"));

        /*
        //http://mongodb.github.io/mongo-java-driver/3.4/driver-async/tutorials/databases-collections/

        MongoClient client = MongoClients.create("mongodb://localhost:27017");
        MongoIterable<String> dbs = client.listDatabaseNames();
        client.listDatabaseNames().forEach(new Block<String>() {
            @Override
            public void apply(final String s) {
                System.out.println(s);
            }
        }, new SingleResultCallback<Void>() {
            @Override
            public void onResult(Void result, Throwable t) {
                System.out.println("Operation Finished!");
            }
        });
        */





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

    private static JSONObject getDefaultConfig(){
        JSONObject defaultConfig = new JSONObject();

        defaultConfig.put("name", APP_NAME);
        defaultConfig.put("version", APP_VERSION);
        defaultConfig.put("mode", "dev"); // prod
        defaultConfig.put("secret", AppRandom.getRandomString(128));
        defaultConfig.put("port", 9999);
        defaultConfig.put("threads_min", 2);
        defaultConfig.put("threads_max", 8);
        defaultConfig.put("threads_idle_timeout_ms", 30000);
        defaultConfig.put("mongodb_connectionstring", "mongodb://localhost:27017");
        defaultConfig.put("mongodb_database", "sparkapp_db");
        return defaultConfig;
    }




    public static CommandLine parseArgs(String[] args ){
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        Options options = new Options();
        try{


            options.addOption(Option.builder("c").longOpt("config").desc("Path to config.json")
                    .hasArg(true).argName("config.json").required(false).build());
            options.addOption(Option.builder("i").longOpt("init").desc("Create empty template config.json").build());
            options.addOption("v", "version", false, "Display current application version");
            options.addOption("h", "help", false, "Show usage and help information");

            cmd = parser.parse(options, args);

            if(cmd.hasOption("c")){
                Path p = Paths.get(cmd.getOptionValue("c").trim());
                Path fullPath = p.toAbsolutePath().normalize();
                if(Files.exists(fullPath, LinkOption.NOFOLLOW_LINKS)){
                    byte[] configData = Files.readAllBytes(fullPath);
                    try{
                        JSONObject configJSON = new JSONObject(new String(configData));
                        config = configJSON;
                    }catch(JSONException ex){
                        System.out.println("Error: "+fullPath+" is not a valid json file.");
                        System.out.println(ex.getMessage());
                        System.exit(0);
                    }
                }else{
                    System.out.println("Error: "+fullPath+" not found.");
                    System.exit(0);
                }
            }

            if(cmd.hasOption("i")){
                Path appDir = Paths.get(".").toAbsolutePath().normalize();
                Path configFilePath = Paths.get(appDir.toString(),"config.json");
                try{
                    if(Files.exists(configFilePath, LinkOption.NOFOLLOW_LINKS)){
                        System.out.println("Error: "+configFilePath+" already exists. Cowardly refusing to overwrite it.");
                    }else{
                        Files.write(configFilePath, getDefaultConfig().toString(4).getBytes(), StandardOpenOption.CREATE_NEW);
                        System.out.println("Success: "+configFilePath+" successfully created. Modify it to create your own settings.");
                    }
                }catch(IOException ex){
                    ex.printStackTrace();
                    System.exit(1);
                }
                System.exit(0);
            }
            if(cmd.hasOption("h")){
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp(CMD_DEFAULT, CMD_HEADER, options, CMD_FOOTER, true);
                System.exit(0);
            }
            if(cmd.hasOption("v")){
                System.out.println(APP_VERSION);
                System.exit(0);
            }


        } catch (ParseException ex){
            System.out.println(ex.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(CMD_DEFAULT, CMD_HEADER, options, CMD_FOOTER, true);
            System.exit(0);
        } finally {
            return cmd;
        }
    }



}
