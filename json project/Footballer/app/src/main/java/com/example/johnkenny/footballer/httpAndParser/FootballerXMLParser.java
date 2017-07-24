package com.example.johnkenny.footballer.httpAndParser;



import com.example.johnkenny.footballer.Footballer;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by johnkenny on 17/03/2017.
 */

public class FootballerXMLParser {

    public static List<Footballer> parseFeed(String content) {

        try {
            // this will be set to true when a footballer tag is found
            boolean inDataItemTag = false;
            //The currnet tag name
            String currentTagName = "";
            //the current footballer object
            Footballer footballer = null;
            //List of Footballers to be populated from the XML
            List<Footballer> footballers = new ArrayList<>();

            //CREATES AN XmlPullParserFactory instance
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            //the xml retireved from the server will be passed in here from the footballerListFragmnent
            parser.setInput(new StringReader(content));

            int eventType = parser.getEventType();

            // XMLPullParser generates events. Once for each start tag, end tag and for text events
            while (eventType != XmlPullParser.END_DOCUMENT) {

                //
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        //sets the current tag
                        currentTagName = parser.getName();

                        // when a footballer footballer start tag is found
                        if (currentTagName.equals("footballer")) {
                            //set to true
                            inDataItemTag = true;
                            //create a new footballer object
                            footballer = new Footballer();
                            //add the footballer object to the list
                            footballers.add(footballer);
                        }
                        break;
                    //end of this footbaler pbkect
                    case XmlPullParser.END_TAG:
                        // if it is the close tag of the footballer
                        if (parser.getName().equals("footballer")) {
                            inDataItemTag = false;
                        }
                        currentTagName = "";
                        break;

                    case XmlPullParser.TEXT:
                        //if the footballer object is null set its values
                        if (inDataItemTag && footballer != null) {
                            switch (currentTagName) {
                                case "name":
                                    //set the footballer name
                                    footballer.setName(parser.getText());
                                    break;
                                case "team":
                                    //set the footballer
                                    footballer.setTeam(parser.getText());
                                    break;
                                case "number":
                                    //set the footballer number
                                    footballer.setNumber(Integer.parseInt(parser.getText()));
                                    break;
                                case "dob":
                                    //set the footballers dob
                                    footballer.setDob(parser.getText());
                                    break;
                                case "position":
                                    //set the footballers position
                                    footballer.setPostion(parser.getText());
                                    break;
                                case "playing":
                                    //set the playing boolean
                                    if(parser.getText().equalsIgnoreCase("true")){
                                        footballer.setPlaying(1);
                                    }
                                    else{
                                        footballer.setPlaying(0);
                                    }
                                    break;
                                case "photo":
                                    //set the footballers image
                                    footballer.setImageLoc(parser.getText());
                                    break;

                                default:
                                    break;
                            }
                        }
                        break;
                }
                //moves to the next event - footballer if any
                eventType = parser.next();

            }

            // returns a full list of footballers generated using the XML
            return footballers;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
