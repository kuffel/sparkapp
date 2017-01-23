package eu.kuffel.sparkapp.routes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Map;
import java.util.Set;

/**
 *
 * @author kuffel
 * @version 06.01.2017
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

        responseJSON.put("method",request.requestMethod());
        responseJSON.put("host",request.host());
        responseJSON.put("ip",request.ip());
        responseJSON.put("scheme",request.scheme());
        responseJSON.put("url",request.url());
        responseJSON.put("uri",request.uri());
        responseJSON.put("userAgent",request.userAgent());


        JSONObject attributesJson = new JSONObject();
        Set<String> attributeNames = request.attributes();
        for(String a : attributeNames){
            attributesJson.put(a, String.valueOf(request.attribute(a)));
        }
        responseJSON.put("attributes",attributesJson);


        JSONObject headersJson = new JSONObject();
        Set<String> headerNames = request.headers();
        for(String h : headerNames){
            headersJson.put(h, request.headers(h));
        }
        responseJSON.put("headers",headersJson);


        JSONObject paramsJson = new JSONObject();
        Set<String> paramNames = request.params().keySet();
        for(String p : paramNames){
            paramsJson.put(p, request.params(p));
        }
        responseJSON.put("params",paramsJson);

        JSONObject queryMapJson = new JSONObject();
        Set<String> queryMapNames = request.queryParams();
        for(String q : queryMapNames){
            queryMapJson.put(q, request.queryParams(q));
        }
        responseJSON.put("query",queryMapJson);


        responseJSON.put("body",request.body());


        if(request.contentType().contains("application/json")){
            try {
                JSONObject jsonBody = new JSONObject(request.body());
                System.out.println(jsonBody);
            } catch (JSONException ex){
                ex.printStackTrace();
            }
        }



        responseJSON.put("contentLength",request.contentLength());
        responseJSON.put("contentType",request.contentType());
        responseJSON.put("contextPath",request.contextPath());

        /*
        request.attributes();             // the attributes list
        request.attribute("foo");         // value of foo attribute
        request.attribute("A", "V");      // sets value of attribute A to V
        request.body();                   // request body sent by the client
        request.bodyAsBytes();            // request body as bytes
        request.contentLength();          // length of request body
        request.contentType();            // content type of request.body
        request.contextPath();            // the context path, e.g. "/hello"
        request.cookies();                // request cookies sent by the client
        request.headers();                // the HTTP header list
        request.headers("BAR");           // value of BAR header
        request.host();                   // the host, e.g. "example.com"
        request.ip();                     // client IP address
        request.params("foo");            // value of foo path parameter
        request.params();                 // map with all parameters
        request.pathInfo();               // the path info
        request.port();                   // the server port
        request.protocol();               // the protocol, e.g. HTTP/1.1
        request.queryMap();               // the query map
        request.queryMap("foo");          // query map for a certain parameter
        request.queryParams();            // the query param list
        request.queryParams("FOO");       // value of FOO query param
        request.queryParamsValues("FOO")  // all values of FOO query param
        request.raw();                    // raw request handed in by Jetty
        request.requestMethod();          // The HTTP method (GET, ..etc)
        request.scheme();                 // "http"
        request.servletPath();            // the servlet path, e.g. /result.jsp
        request.session();                // session management
        request.splat();                  // splat (*) parameters
        request.uri();                    // the uri, e.g. "http://example.com/foo"
        request.url();                    // the url. e.g. "http://example.com/foo"
        request.userAgent();              // user agent
        */


        return responseJSON;
    }

}
