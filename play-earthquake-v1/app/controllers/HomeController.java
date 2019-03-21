package controllers;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import play.mvc.*;

import views.html.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;


import com.google.gson.*;
import com.google.gson.Gson;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public Result returnCoordinates() throws MalformedURLException, ProtocolException, IOException {
        ArrayList<JsonElement> coordinateList = new ArrayList<>();
        URL url = new URL("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&latitude=35.2&longitude=139.83&maxradiuskm=100&minmagnitude=5");

        //http://chillyfacts.com/java-send-http-getpost-request-and-read-json-response
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JsonParser JsonParser = new JsonParser();


        JsonElement jsonTree = JsonParser.parse(response.toString());
        System.out.println(jsonTree);
        JsonObject test = (JsonObject) jsonTree;

        JsonElement mainElement = test.get("features");

        JsonArray main = mainElement.getAsJsonArray();

        for (int i = 0; i < main.size(); i++) {
            JsonElement first = main.get(i);
            JsonObject inside = first.getAsJsonObject();
            JsonElement geometry = inside.get("geometry");
            JsonObject coordinates_object = geometry.getAsJsonObject();
            JsonElement coordinates = coordinates_object.get("coordinates");
            JsonArray last = coordinates.getAsJsonArray();

            coordinateList.add(coordinates);
            //System.out.println(coordinates);
            //coordinate.add(new Coordinate(last.get(0), last.get(1)));

            //System.out.print(last.get(0) + " ");
            //System.out.println(last.get(1));
            //test
            //merge test
        }
        return ok(coordinateList.toString());
    }
}
