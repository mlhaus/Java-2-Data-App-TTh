package edu.kirkwood.dao.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import edu.kirkwood.dao.MovieDAO;
import edu.kirkwood.model.Movie;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class JsonMovieDAO implements MovieDAO {
    private String apiURL;
    private String apiToken;
    public JsonMovieDAO(String apiURL, String apiToken) {
        this.apiURL = apiURL;
        this.apiToken = apiToken;
    }

    public String getRawData(String title) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(apiURL + "query=" + title) // Todo: Add page number
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer " + apiToken)
                .build();
        Response response = null;
        String responseBody = "";
        try {
            response = client.newCall(request).execute();
            responseBody = response.body().string();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        return responseBody;
    }

    @Override
    public List<Movie> search(String title) {
        String rawData = getRawData(title);
        System.out.println(prettyFormatter(rawData));
        return List.of();
    }
    
    public String prettyFormatter(String rawData) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        JsonElement jsonElement = new JsonParser().parse(rawData);
        return gson.toJson(jsonElement);
    }
}
