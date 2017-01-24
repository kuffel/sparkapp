package eu.kuffel.sparkapp.utils;

import spark.QueryParamsMap;
import spark.Request;
import spark.Session;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Extends the basic spark request object with a few useful methods.
 *
 * @author kuffel
 * @version 06.01.2017
 */
public class AppRequest extends Request {

    private Request requestRaw;

    public AppRequest( Request request ){
        Objects.nonNull(request);
        requestRaw = request;
    }


    @Override
    public Map<String, String> params() {
        return requestRaw.params();
    }

    @Override
    public String params(String param) {
        return requestRaw.params(param);
    }

    @Override
    public String[] splat() {
        return requestRaw.splat();
    }

    @Override
    public String requestMethod() {
        return requestRaw.requestMethod();
    }

    @Override
    public String scheme() {
        return requestRaw.scheme();
    }

    @Override
    public String host() {
        return requestRaw.host();
    }

    @Override
    public String userAgent() {
        return requestRaw.userAgent();
    }

    @Override
    public int port() {
        return requestRaw.port();
    }

    @Override
    public String pathInfo() {
        return requestRaw.pathInfo();
    }

    @Override
    public String servletPath() {
        return requestRaw.servletPath();
    }

    @Override
    public String contextPath() {
        return requestRaw.contextPath();
    }

    @Override
    public String url() {
        return requestRaw.url();
    }

    @Override
    public String contentType() {
        return requestRaw.contentType();
    }

    @Override
    public String ip() {
        return requestRaw.ip();
    }

    @Override
    public String body() {
        return requestRaw.body();
    }

    @Override
    public byte[] bodyAsBytes() {
        return requestRaw.bodyAsBytes();
    }

    @Override
    public int contentLength() {
        return requestRaw.contentLength();
    }

    @Override
    public String queryParams(String queryParam) {
        return requestRaw.queryParams(queryParam);
    }

    @Override
    public String[] queryParamsValues(String queryParam) {
        return requestRaw.queryParamsValues(queryParam);
    }

    @Override
    public String headers(String header) {
        return requestRaw.headers(header);
    }

    @Override
    public Set<String> queryParams() {
        return requestRaw.queryParams();
    }

    @Override
    public Set<String> headers() {
        return requestRaw.headers();
    }

    @Override
    public String queryString() {
        return requestRaw.queryString();
    }

    @Override
    public void attribute(String attribute, Object value) {
        requestRaw.attribute(attribute, value);
    }

    @Override
    public <T> T attribute(String attribute) {
        return requestRaw.attribute(attribute);
    }

    @Override
    public Set<String> attributes() {
        return requestRaw.attributes();
    }

    @Override
    public HttpServletRequest raw() {
        return requestRaw.raw();
    }

    @Override
    public QueryParamsMap queryMap() {
        return requestRaw.queryMap();
    }

    @Override
    public QueryParamsMap queryMap(String key) {
        return requestRaw.queryMap(key);
    }

    @Override
    public Session session() {
        return requestRaw.session();
    }

    @Override
    public Session session(boolean create) {
        return requestRaw.session(create);
    }

    @Override
    public Map<String, String> cookies() {
        return requestRaw.cookies();
    }

    @Override
    public String cookie(String name) {
        return requestRaw.cookie(name);
    }

    @Override
    public String uri() {
        return requestRaw.uri();
    }

    @Override
    public String protocol() {
        return requestRaw.protocol();
    }

}
