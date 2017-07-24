package com.example.johnkenny.footballer.httpAndParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by johnkenny on 07/02/2017.
 */

/*

This class is used to manage a http connection and read the data from a file line by
line
 */

public class HttpManager {

    public static String getData(String uri) {
        //create a BufferedReader object
        BufferedReader reader = null;
        //try to open a connection
        try {
            //creates a new url object
            URL url = new URL(uri);
            //creates
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            //creates a new StringBuilder object
            StringBuilder sb = new StringBuilder();
            //sets the reader to the content from the connection input (the xml data)
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;

            // the reader will contnue to read in from the input stream until everything is read in
            while ((line = reader.readLine()) != null) {
                //append and add a line break
                sb.append(line + "\n");
            }
            return sb.toString();
        }
        //catch errors
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        //when complete
        finally {
            //if the buffer reader object has content
            if (reader != null) {
                try {
                    //clsoe the reader
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }


    }

}
