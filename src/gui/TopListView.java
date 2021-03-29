package gui;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class TopListView {

    private String url;
    @FXML
    private WebView webView;
    @FXML
    private StackPane stackPane;
    private double zoom = 0.85;

    public void init() throws IOException {

        //Creates a new stage.
        Stage stage = new Stage();

        //Loads the FXML file.
        Parent root = FXMLLoader.load(getClass().getResource("TopListScreen.fxml"));

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

    public void showWeb() {

        //Makes sure that the web browser does not open if the link does not include MyAnimeList.
        /*if(!url.contains("myanimelist")) {
            url = "";
            System.out.println("URL Does not contain 'MyAnimeList'");
        }*/

        //Gets the URL from the button that is clicked.
        String urlweb = url;
        webView.setZoom(zoom);
        WebEngine web = webView.getEngine();
        web.load(urlweb);

    }

    public void handleTopAiringButton()
    {
        url = "https://myanimelist.net/topanime.php?type=airing";
        showWeb();
    }

    public void handleTopUpcomingButton()
    {
        url = "https://myanimelist.net/topanime.php?type=upcoming";
        showWeb();
    }

    public void handleTopSeriesButton()
    {
        url = "https://myanimelist.net/topanime.php?type=tv";
        showWeb();
    }

    public void handleTopMoviesButton()
    {
        url = "https://myanimelist.net/topanime.php?type=movie";
        showWeb();
    }
    public void handleZoomInButton() {

        //Increases zoom by 0.2.
        double newZoom = zoom += 0.2;
        if(newZoom >=2) {
            JOptionPane.showMessageDialog(null, "You can't zoom in more!");
            //Makes sure the values cant go higher by setting them to 2.
            newZoom = 2;
            zoom = 2;
        }
        webView.setZoom(newZoom);
        //System.out.println(newZoom);

    }

    public void handleZoomOutButton() {

        //Decreases zoom by 0.2.
        double newZoom = zoom -= 0.2;
        if(newZoom <= 0) {
            JOptionPane.showMessageDialog(null, "You can't zoom out more!");
            //Makes sure the values cant go lower by setting them to 0.
            newZoom = 0;
            zoom = 0;
        }
        webView.setZoom(newZoom);
        //System.out.println(newZoom);

    }

    public void handleBackButton() throws IOException {

        //Hides the window where the webView is located.
        webView.getScene().getWindow().hide();

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
