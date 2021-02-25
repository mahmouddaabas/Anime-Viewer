package gui;

import data.ContactAPI;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.json.JSONException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class View extends Application
{
    @FXML
    private Label idLbl, titleLbl, episodesLbl, scoreLbl, ratedLbl;
    @FXML
    private TextArea descriptionTxtArea;
    @FXML
    private ImageView showImage;
    @FXML
    private TextField searchText;

    @Override
    public void start(Stage stage) throws Exception {

        //Loads the FXML file.
        Parent root = FXMLLoader.load(getClass().getResource("screen.fxml"));

        //Adds background image to the program. (This works)
        String image = View.class.getResource("images/background2.png").toExternalForm();
        root.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");

        stage.setTitle("Anime Viewer");
        stage.setResizable(false);
        Scene s = new Scene(root);
        stage.setScene(s);
        stage.show();

    }

    public void getData() throws InterruptedException, JSONException, IOException {
        //Saves the search in a String variable so that it can later be sent to the ContactAPI class.
        String sendKeyword = searchText.getText();

        //Create a data object.
        ContactAPI data = new ContactAPI();
        //Send whats written into the search textfield to the ContactAPI class. It's used in the replace method which replaces space bars with %20.
        data.setKeyword(replace(sendKeyword));
        //Call the method to populate the getters and setters.
        data.getData();

        idLbl.setText("ID: " + data.getId());
        titleLbl.setText("Title: " + data.getTitle());
        episodesLbl.setText("Episodes: " + data.getEpisodes());
        scoreLbl.setText("Score: " + data.getScore());
        ratedLbl.setText("Rated: " + data.getRated());
        descriptionTxtArea.setText(data.getSynopsis());

        //Set the image in the ImageView from a URL that is delivered from the API.
        String path = data.getImageUrl();
        BufferedImage image;
        URL url = new URL(path);
        image = ImageIO.read(url);
        Image img = SwingFXUtils.toFXImage(image, null);
        showImage.setPreserveRatio(false);
        showImage.setFitWidth(235);
        showImage.setFitHeight(185);
        showImage.setImage(img);

    }

    public void searchButton() throws InterruptedException, IOException, JSONException {
        getData();
        System.out.println("Data acquired");
    }

    //Replaces spaces from in string with %20 if there is any.
    public static String replace(String s) {
        s = s.trim();
        s = s.replaceAll(" ", "%20");
        return s;
    }
}
