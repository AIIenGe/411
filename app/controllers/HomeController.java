package controllers;


import Model.Coordinate;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.*;
import io.ebean.Model;
import views.html.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static play.libs.Json.toJson;
import play.data.*;
import com.google.gson.*;
import com.google.gson.Gson;

import javax.inject.Inject;

public class HomeController extends Controller {
    @Inject
    private FormFactory formFactory;
    private Coordinate coordinate;
    //merge element

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public Result addLocation (Http.Request request) {
        Form<Coordinate> coordinateForm = formFactory.form(Coordinate.class).bindFromRequest(request);
        coordinate = coordinateForm.get();
        coordinate.save();
        return redirect(routes.HomeController.returnHeatmap());
    }


    public Result getHistory(){
        List<Coordinate> coordinates = Coordinate.find.all();
        return ok(history.render(toJson(coordinates).toString()));
        //return ok(toJson(coordinates));
    }

    public Result returnHeatmap() throws MalformedURLException, ProtocolException, IOException {
        ArrayList<JsonElement> coordinateList = new ArrayList<>();
        URL url = new URL("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&latitude=" + coordinate.getLatitude() + "&longitude=" + coordinate.getLongitude() + "&maxradiuskm=100&minmagnitude=5");

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

            JsonElement properties = inside.get("properties");
            JsonObject properties_object = properties.getAsJsonObject();

            JsonElement Time = properties_object.get("time");
            /*
            JsonElement Magnitude = properties_object.get("mag");
            JsonElement Place = properties_object.get("place");


            JsonElement geometry = inside.get("geometry");
            JsonObject coordinates_object = geometry.getAsJsonObject();
            JsonArray coordinates = coordinates_object.get("coordinates").getAsJsonArray();

            JsonElement Longitude = coordinates.get(0);
            JsonElement Latitude = coordinates.get(1);
            */
            JsonElement Title = properties_object.get("title");



            //https://stackoverflow.com/questions/17432735/convert-unix-time-stamp-to-date-in-java

            BigInteger bd = Time.getAsBigInteger();
            Long value = bd.divide(new BigInteger("1000")).longValue();

            java.util.Date unixSeconds =new java.util.Date((long)value * 1000);


            //JsonArray last = coordinates.getAsJsonArray();

            JsonArray combined = new JsonArray();
            combined.add(Title);
            combined.add(unixSeconds.toString());



            coordinateList.add(combined);
            //3.0 69km SSW of Kobuk, Alaska 2019-04-16 23:55:25 (UTC)
            //#1 MinMagnitude: textbox
            //#2 Start Date: textbox
            //#3 End Date: textbox
            //#4 Radius: textox
            //
            //#5 World: Radio Button
            //#6 Latitude: textbox
            //#7 Longitude: textbox
        }
        return ok(heatMap.render(coordinate, coordinateList.toString()));

    }
}
