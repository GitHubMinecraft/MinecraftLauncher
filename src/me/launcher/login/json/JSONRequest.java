package me.launcher.login.json;

/**
 * Created by User on 28/05/2015.
 */
public class JSONRequest {
    private Agent agent;
    private String username;
    private String password;
    private String clientToken;

    public JSONRequest(Agent agent, String username, String password, String clientToken) {
        this.agent = agent;
        this.username = username;
        this.password = password;
        this.clientToken = clientToken;
    }
}
