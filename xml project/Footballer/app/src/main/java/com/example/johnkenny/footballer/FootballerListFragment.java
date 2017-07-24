package com.example.johnkenny.footballer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.johnkenny.footballer.httpAndParser.FootballerXMLParser;
import com.example.johnkenny.footballer.httpAndParser.HttpManager;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.johnkenny.footballer.R.id.imageView;
import static com.example.johnkenny.footballer.helperClass.imageConvertor.getImage;

/**
 * Created by johnkenny on 20/01/2017.
 */

public class FootballerListFragment extends ListFragment {

    //class level vars
    private static final String TAG = "FootballerListFragment";
    private ArrayList<Footballer> mfootballers;
    private static final String imageLink = "http://10.0.2.2/android/images/";


    //onCreate method
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //creates a new async task
        MyTask task = new MyTask();
        task.execute("Param 1", "Param 2", "Param 3");
    }//close onCreate method

    //when an footballer on the list is clicked
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //gets this clicked footballer object
        Footballer footballer = (Footballer) (getListAdapter()).getItem(position);
        //used for testing
        Log.d(TAG, footballer.getName() + " was clicked");


    }//onListItemClick

    //a new class used to populate the list with the footballer
    //using each footballer object and the layout items from the xml file
    private class FootballAdapter extends ArrayAdapter<Footballer> {

        // Constructor
        public FootballAdapter(ArrayList<Footballer> f) {
            super(getActivity(), 0, f);

        }


        //   @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //checks for a list viev
            if (null == convertView) {
                //if there is no list view one is inflated here
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_footballer, null);
            }

            // configure the view for this footballer
            Footballer f = getItem(position);

            //set the footballer name in the main text view using the footballer object
            TextView titleTextView =
                    (TextView) convertView.findViewById(R.id.name);
            titleTextView.setText(f.getName());

            //Sets the footballer number displayed on the right of each item
            TextView noTextView =
                    (TextView) convertView.findViewById(R.id.f_num);
            noTextView.setText(String.valueOf(f.getNumber()));

            //sets the footballers current team
            TextView ctTextView =
                    (TextView) convertView.findViewById(R.id.currentteam);
            ctTextView.setText(f.getTeam());

            //sets teh footballers position
            TextView posTextView =
                    (TextView) convertView.findViewById(R.id.pos);
            posTextView.setText(f.getPostion());

            /* set the image to the left of the listview item*/
            ImageView iv = (ImageView) convertView.findViewById(imageView);

            //checks if this footballer object has an image set
            if (f.getBlobber() != null) {
                /*if the footballer has an image use the helper class to convert the
                byte array to a bitmap and display this bitmap in the imageview
                 */
                iv.setImageBitmap(getImage(f.getBlobber()));
            }
            else if (f.getBitmap() != null) {
                /*if the footballer has an image use the helper class to convert the
                byte array to a bitmap and display this bitmap in the imageview
                 */
                iv.setImageBitmap(f.getBitmap());
            }
            else {
                //if the object has no image use the default image from the drawable folder
                iv.setImageResource(R.drawable.pl);
            }

            //sets the colours of the list items. Every second item a differnt color
            if (position % 2 == 1) {
                convertView.setBackgroundColor(Color.rgb(0, 242, 253));
            } else {
                convertView.setBackgroundColor(Color.rgb(0, 255, 149));
            }
            return convertView;
        }
    }// End of the inner class


    /* this method is for when we return to this screen from another screen*/
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onResume() {
        super.onResume();
    }


    /*
        this method is called and used to update the ui when all data is
        downloaded from the server, the adapter is then used to populate the list
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void updateUI() {
        //creates a footballAdapter using the array list of footballer objects
        FootballAdapter footballAdapter = new FootballAdapter(mfootballers);
        //sets the setListAdapter using the footballAdapter just created
        setListAdapter(footballAdapter);

    }//updateUI

    /*
        this is new private class only used in this class. This class
        extends
     */
    private class MyTask extends AsyncTask<String, String, List<Footballer>> {

        HttpManager manager = new HttpManager();
        String players;
        FootballerXMLParser parser = new FootballerXMLParser();


        @Override
        protected void onPreExecute() {
            //updateDisplay("Starting task");
            //toggleVisibility();

        }

        @Override
        protected List <Footballer> doInBackground(String... params) {

            //gets the xml data from the server and saves it as a string
            String contents = manager.getData("http://10.0.2.2/android/footballers.xml");
            //prints the data - used for testing
            System.out.println(contents);
            //fills the footballers array list with parsered list of footballer objects
            mfootballers = (ArrayList<Footballer>) parser.parseFeed(contents);
            //loops through all the fooballer objects in the arraylist
            for (int i =0; i < mfootballers.size(); i++) {
                //try to get the images from the server
                try {
                    //the name of the cuurent footballer printed fro testing
                    System.out.println(mfootballers.get(i).getName());
                    //creates the image url using the link and the location previously downloaded in the xml
                    String imageUrl = imageLink + mfootballers.get(i).getImageLoc();
                    //creates an inputStream to download the image from the server
                    InputStream inputStream = (InputStream) new URL(imageUrl).getContent();
                    //creates a bitmpa image opject by decoding the downloaded file
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    //sets the image to the footbalers butmap
                    mfootballers.get(i).setBitmap(bitmap);
                    //closes the inputStream as the image is downloaded
                    inputStream.close();
                //error handeling
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            //returns the list of footballers
            return mfootballers;
        }


        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

        }

        //when the data is retrieved from the server
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        protected void onPostExecute(List <Footballer> f) {
            //calls the update ui to update with the new data
            updateUI();

        }
    }
}

