package run;

import org.json.JSONException;

import java.io.IOException;

//Alternate main class that doesn't extend Application. Basically calls the other main class.

public class AlternateMain
{
    public static void main(String[] args) throws InterruptedException, JSONException, IOException {
        run.Main.main(args);
    }
}
