package me.launcher.login;

import com.google.gson.Gson;
import javafx.concurrent.Task;
import me.launcher.httputil.HTTPRequest;
import me.launcher.httputil.HTTPResponse;
import me.launcher.login.json.Agent;
import me.launcher.login.json.JSONRequest;
import me.launcher.login.json.JSONResponse;
import me.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by User on 28/05/2015.
 */
public class AuthenticationThread extends Task<Void> {
    private String username;
    private String password;
    public boolean authenticated;
    public JSONResponse response;

    public AuthenticationThread(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    protected Void call() throws Exception {
        try {
            Log.out("Started Authentication");
            Gson gson = new Gson();
            JSONRequest json = new JSONRequest(new Agent("Minecraft", 1), username, password, null);
            HTTPRequest request = new HTTPRequest("authenticate", gson.toJson(json));
            HTTPResponse response = new HTTPResponse(request.send());
        } catch (Exception e) {
            Log.err(e);
        }

        return null;
    }
}
