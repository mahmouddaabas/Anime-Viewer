package run;

import data.ContactAPI;
import gui.View;
import javafx.application.Application;
import org.json.JSONException;

import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException, InterruptedException, JSONException {

        Application.launch(View.class, args);

    }
}
