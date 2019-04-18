package controllers;


import Model.Earthquake;
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
    private Earthquake earthquake;
    //merge element

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public Result addLocation (Http.Request request) {
        Form<Earthquake> earthquakeForm = formFactory.form(Earthquake.class).bindFromRequest(request);
        earthquake = earthquakeForm.get();
        earthquake.save();
        return redirect(routes.HomeController.returnHeatmap());
    }

    public Result returnHeatmap() {
        return ok(heatMap.render(earthquake));
    }

    public Result getHistory() {
        List<Earthquake> earthquakes = Earthquake.find.all();
        return ok(history.render(toJson(earthquakes).toString()));
        //return ok(toJson(coordinates));
    }
}
