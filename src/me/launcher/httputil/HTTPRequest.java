package me.launcher.httputil;

import me.util.Log;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by User on 28/05/2015.
 */
public class HTTPRequest {
    private final String AUTH_SERVER = "https://authserver.mojang.com/";

    public String subAddress;
    public String content;

    public HTTPRequest(String subAddress, String content) {
        this.subAddress = subAddress;
        this.content = content;
    }

    public HttpsURLConnection send() {
        try {
            HttpsURLConnection con = (HttpsURLConnection) new URL(AUTH_SERVER + subAddress).openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            byte[] bytes = content.getBytes("UTF-8");
            con.setRequestProperty("Content-Length", String.valueOf(bytes.length));
            con.setDoOutput(true);
            con.getOutputStream().write(bytes);
            con.getOutputStream().flush();
            return con;
        } catch (IOException e) {
            Log.err(e);
            return null;
        }
    }
}
