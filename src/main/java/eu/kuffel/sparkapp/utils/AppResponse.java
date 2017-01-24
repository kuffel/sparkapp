package eu.kuffel.sparkapp.utils;

import spark.Response;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * Extends the basic spark response object with a few useful methods.
 *
 * @author kuffel
 * @version 06.01.2017
 */
public class AppResponse extends Response {

    private Response responseRaw;

    public AppResponse( Response response ){
        Objects.nonNull(response);
        responseRaw = response;
    }

    @Override
    public void status(int statusCode) {
        responseRaw.status(statusCode);
    }

    @Override
    public int status() {
        return responseRaw.status();
    }

    @Override
    public void type(String contentType) {
        responseRaw.type(contentType);
    }

    @Override
    public String type() {
        return responseRaw.type();
    }

    @Override
    public void body(String body) {
        responseRaw.body(body);
    }

    @Override
    public String body() {
        return responseRaw.body();
    }

    @Override
    public HttpServletResponse raw() {
        return responseRaw.raw();
    }

    @Override
    public void redirect(String location) {
        responseRaw.redirect(location);
    }

    @Override
    public void redirect(String location, int httpStatusCode) {
        responseRaw.redirect(location, httpStatusCode);
    }

    @Override
    public void header(String header, String value) {
        responseRaw.header(header, value);
    }

    @Override
    public void cookie(String name, String value) {
        responseRaw.cookie(name, value);
    }

    @Override
    public void cookie(String name, String value, int maxAge) {
        responseRaw.cookie(name, value, maxAge);
    }

    @Override
    public void cookie(String name, String value, int maxAge, boolean secured) {
        responseRaw.cookie(name, value, maxAge, secured);
    }

    @Override
    public void cookie(String name, String value, int maxAge, boolean secured, boolean httpOnly) {
        responseRaw.cookie(name, value, maxAge, secured, httpOnly);
    }

    @Override
    public void cookie(String path, String name, String value, int maxAge, boolean secured) {
        responseRaw.cookie(path, name, value, maxAge, secured);
    }

    @Override
    public void cookie(String path, String name, String value, int maxAge, boolean secured, boolean httpOnly) {
        responseRaw.cookie(path, name, value, maxAge, secured, httpOnly);
    }

    @Override
    public void removeCookie(String name) {
        responseRaw.removeCookie(name);
    }
}
