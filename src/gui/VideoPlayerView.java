package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;

public class VideoPlayerView

{
    @FXML
    private Button backBtn, p;
    @FXML
    private WebView webView;

    public void init() throws IOException {

        //Creates a new stage.
        Stage stage = new Stage();

        //Loads the FXML file.
        Parent root = FXMLLoader.load(getClass().getResource("VideoPlayerView.fxml"));

        //Adds background image to the program. (This works)
        String image = View.class.getResource("images/background.png").toExternalForm();
        root.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");

        //Sets the application logo and other default things.
        stage.getIcons().add(new Image(View.class.getResourceAsStream("images/icon.jpg")));
        stage.setTitle("Anime Viewer - Top List");
        stage.setResizable(false);
        Scene s = new Scene(root);
        stage.setScene(s);
        stage.show();
    }


    public void playMedia() throws MalformedURLException {

        String url = "https://youtu.be/jCUbp-BCWR0";
        WebEngine web = webView.getEngine();
        web.load(url);

    }

    public void handlePlayButton() throws MalformedURLException {
        //playMedia();
    }

    public void handleBackButton() throws IOException {

        //Hides the window where the webView is located.
        backBtn.getScene().getWindow().hide();

        //Creates a new stage.
        Stage stage = new Stage();

        //Loads the FXML file.
        Parent root = FXMLLoader.load(getClass().getResource("screen.fxml"));

        //Adds background image to the program. (This works)
        String image = View.class.getResource("images/background.png").toExternalForm();
        root.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");

        //Sets the application logo and other default things.
        stage.getIcons().add(new Image(View.class.getResourceAsStream("images/icon.jpg")));
        stage.setTitle("Anime Viewer - Main View");
        stage.setResizable(false);
        Scene s = new Scene(root);
        stage.setScene(s);
        stage.show();

    }
}
