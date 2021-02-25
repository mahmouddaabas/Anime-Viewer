package data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ContactAPI
{
    private int id, episodes;
    private double score;
    private String url, imageUrl, title, synopsis, rated, keyword;

    public ContactAPI() throws IOException, InterruptedException, JSONException {
        getData();
    }

    public void getData() throws IOException, InterruptedException, JSONException {

        try {

        //The variable keyword is used in a method in the View class that replaces the space bars with %20 before its sent here.
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jikan1.p.rapidapi.com/search/anime?q=" + keyword))
                .header("x-rapidapi-key", "f6aa8129a5msh0a737827b7ea042p182960jsn0c0a7e40ab8a")
                .header("x-rapidapi-host", "jikan1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        //Parse the data from the API.
        //Creates an object with the JSON response.
        JSONObject obj = new JSONObject(response.body());
        //Creates an array to access the results array inside the JSON response.
        JSONArray arr = obj.getJSONArray("results");
        //Loop to get information i < 1 so it only gets 1 result, loop only goes 1 time.
        for(int i = 0; i < 1; i++) {
            id = arr.getJSONObject(i).getInt("mal_id");
            url = arr.getJSONObject(i).getString("url");
            imageUrl = arr.getJSONObject(i).getString("image_url");
            title = arr.getJSONObject(i).getString("title");
            synopsis = arr.getJSONObject(i).getString("synopsis");
            episodes = arr.getJSONObject(i).getInt("episodes");
            score = arr.getJSONObject(i).getDouble("score");
            rated = arr.getJSONObject(i).getString("rated");

            System.out.println(id + "\n" + url + "\n" + imageUrl + "\n" + title + "\n" + synopsis+ "\n" + episodes+ "\n" + score+ "\n" + rated);
        }
        }
        catch(RuntimeException e) {
            JOptionPane.showMessageDialog(null, "Insert a keyword!");
        }
        catch(JSONException e) {
            JOptionPane.showMessageDialog(null, "Insert a keyword!");
        }


    }

    public int getId() {
        return id;
    }

    public int getEpisodes() {
        return episodes;
    }

    public double getScore() {
        return score;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getRated() {
        return rated;
    }

    //Gets the keyword that's put in the search bar in the GUI.
    public void setKeyword(String keywordFromView) {
        this.keyword = keywordFromView;
        System.out.println(keyword);
    }
}
