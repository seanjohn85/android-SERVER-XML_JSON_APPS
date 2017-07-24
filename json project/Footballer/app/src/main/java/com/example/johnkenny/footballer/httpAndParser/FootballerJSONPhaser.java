package com.example.johnkenny.footballer.httpAndParser;

import com.example.johnkenny.footballer.Footballer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johnkenny on 13/03/2017.
 */

public class FootballerJSONPhaser {
    //method that returns a lisst of footballer objects, the downloaded json string is passed into this method
    public static List<Footballer> parseFeed(String content) {
        //creates a list of footballers to return
        List<Footballer> players = new ArrayList<>();
        //try to extract objects from the Json
        try {
           // creates creates json object using the content string param - The json data downloded from the server
            JSONObject downloadedJSON = new JSONObject(content);

            //gets a JSON array of footballers from the JSON object downloadedJSON
            JSONArray footballers = downloadedJSON.getJSONArray("footballers");

            //loops through the json array of footballers converting each footballer into a footballer object
            // and apending it tot the returned arraylist
            for (int i = 0; i < footballers.length(); i++) {
                //creates a new footballer object
                Footballer f = new Footballer();
                //gets the current json footballer key value pair data array
                JSONObject fb = footballers.getJSONObject(i);
                //sets the footballers name using the name json key
                f.setName(fb.getString("name"));
                //sets the footballers team using the team json key
                f.setTeam(fb.getString("team"));
                //sets the footballers number using the number json key and convering to an int
                f.setNumber(Integer.parseInt(fb.getString("number")));
                //sets the footballers dob using the dob json key
                f.setDob(fb.getString("dob"));
                //sets the footballers position using the position json key
                f.setPostion(fb.getString("position"));
                //checks if the player is curently playing
                if (fb.getString("playing").equalsIgnoreCase("true")){
                    //true
                    f.setPlaying(1);
                }
                else{
                    //false
                    f.setPlaying(0);
                }
                //sets the footballers photo location using the photo json key
                f.setImageLoc(fb.getString("photo"));
                /*
                test log
                Log.d("phasers",fb.getString("name"));
                Log.d("phasers no",fb.getString("number"));*/
                //add the footballer to the list
                players.add(f);
            }
            //catch json errors
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //return the players arraylist
        return players;
    }
}
