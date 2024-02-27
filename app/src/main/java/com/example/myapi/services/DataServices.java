package com.example.myapi.services;

import android.os.StrictMode;

import com.example.myapi.models.States;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DataServices {
    private static ArrayList<States> arrState = new ArrayList<States>();
    public static ArrayList<States> getArrState(){
        String sURL = "https://restcountries.com/v2/all?fields=name,nativeName,borders,flags";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try{
            URL url = new URL(sURL);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));

            JsonArray rootobj = root.getAsJsonArray();

            for(JsonElement je : rootobj){
                JsonObject obj = je.getAsJsonObject();
                JsonElement enteriesname = obj.get("name");
                JsonElement enteriesnative = obj.get("nativeName");
                JsonElement enteriesborder = obj.get("borders");
                JsonElement enteriesflags = obj.get("flags");

                String name = enteriesname.toString().replace("\"", "");
                String nativen = enteriesnative.toString().replace("\"", "");
                String flags = enteriesflags.toString().replace("\"", "");

                ArrayList<String> arrBorders = new ArrayList<>();

                if (enteriesborder != null){
                    JsonArray entriesBordersArr = enteriesborder.getAsJsonArray();

                    for(JsonElement j : entriesBordersArr){
                        String s = j.toString().replace("\"", "");
                        arrBorders.add(s);
                    }
                }

                arrState.add(new States (name, nativen, flags, null));
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return arrState;
    }

}
