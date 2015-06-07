package me.launcher;

import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import me.util.Log;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML WebView view;
    @FXML BorderPane pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        view.getEngine().getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED) {
                EventListener listener = evt -> {
                    String href = ((Element) evt.getTarget()).getAttribute("href");
                    try {
                        Desktop.getDesktop().browse(new URI(href));
                    } catch (IOException | URISyntaxException e) {
                        Log.showErr(e);
                        Log.err(e);
                    }
                    evt.stopPropagation();
                    evt.preventDefault();
                };
                Document doc = view.getEngine().getDocument();
                Element el = doc.getElementById("a");
                NodeList nodes = doc.getElementsByTagName("a");
                for (int i = 0; i < nodes.getLength(); i++) {
                    ((EventTarget) nodes.item(i)).addEventListener("click", listener, false);
                }
            }
            if (newValue == Worker.State.SCHEDULED) {
                view.getEngine().getLoadWorker().cancel();
            }
        });
        view.getEngine().load("http://mcupdate.tumblr.com");
        try {
            pane.setBottom(FXMLLoader.load(getClass().getResource("login/login.fxml")));
        } catch (IOException e) {
            Log.err(e);
        }
        Log.out("Setup Main Window");
    }
}
