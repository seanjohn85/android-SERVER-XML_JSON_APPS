package com.example.johnkenny.footballer;

import android.graphics.Bitmap;


/**
 * Created by john kenny on 09/11/2016..
 * This class is used to create objects
 */

public class Footballer {

    //instance varibles of this object
    private String mId;
    private String team;
    private String imageLoc;
    private String name;
    private int number;
    private int playing;
    private String postion;
    private byte[] blobber;
    private String dob;
    private Bitmap bitmap;


    //Constructors to create objects of this class
    public Footballer(String name) {
        this.name = name;
    }

    public Footballer() {

    }

    //get methods

    public String getImageLoc() {
        return imageLoc;
    }

    public int getNumber() {
        return number;
    }

    public int getPlaying() {
        return playing;
    }

    public String getName() {
        return name;
    }

    public String getPostion() {
        return postion;
    }

    public String getTeam() {
        return team;
    }

    public String getmId() {
        return mId;
    }

    public byte[] getBlobber() {
        return blobber;
    }

    public String getDob() {
        return dob;
    }


    //set methods

    public void setBlobber(byte[] blobber) {
        this.blobber = blobber;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPlaying(int playing) {
        this.playing = playing;
    }

   public void setImageLoc(String imageLoc) {
        this.imageLoc = imageLoc;
    }

    public void setPostion(String postion) {
        this.postion = postion;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }


    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    //print method used for testing only
    @Override
    public String toString() {
        return name + " " + number + " " + postion + " " + team;
    }



}//close Footballer class
