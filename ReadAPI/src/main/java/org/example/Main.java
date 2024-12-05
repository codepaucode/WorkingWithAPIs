package org.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.web.client.RestTemplate;


public class Main {
    public static void main(String[] args) {
        String json = new RestTemplate().getForObject("https://official-joke-api.appspot.com/jokes/ten", String.class);

        // Parse the JSON into a JsonArray
        JsonArray jokesArray = JsonParser.parseString(json).getAsJsonArray();

        // Get the 5th joke (index 4)
        JsonObject fifthJoke = jokesArray.get(4).getAsJsonObject();

        // Extract the "setup" and "punchline" fields
        String setup = fifthJoke.get("setup").getAsString();
        String punchline = fifthJoke.get("punchline").getAsString();

        // Print the 5th joke
        System.out.println(setup + " - " + punchline);
    }
}