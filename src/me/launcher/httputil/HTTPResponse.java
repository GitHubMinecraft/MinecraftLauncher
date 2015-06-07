package me.launcher.httputil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.util.Log;
import me.util.StringUtil;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by User on 31/05/2015.
 */
public class HTTPResponse {
    public String body;

    public HTTPResponse(HttpsURLConnection con) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            body = builder.toString();
        } catch (IOException e) {
            if (e.getLocalizedMessage().contains("HTTP response code")) {
                try {
                    if (con.getResponseCode() != 200) {
                        String response = (String) StringUtil.buildFromReader(new BufferedReader(new InputStreamReader(con.getErrorStream())));
                        HTTPError error = new Gson().fromJson(response.trim(), HTTPError.class);
                        if (error.errorMessage.startsWith("Invalid credentials")) {
                            if (error.cause.equals("UserMigratedException")) {
                            }
                        }
                        if (!Log.DEBUG) {
                            Log.err(error.error + " caused by " + error.cause);
                        } else {
                            Log.err(error.error + " caused by " + error.cause + '\n' + error.errorMessage);
                        }
                    }
                } catch (IOException e1) {
                    Log.err(e1);
                }
            } else {
                Log.err(e);
            }
        }
    }
}
