package com.example.bot.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ResponseService {

    public static JSONObject createJSONObject(String URL) throws IOException {
        String response = getString(URL);
        return new JSONObject(response);
    }

    public static JSONArray createJSONArray(String URL) throws IOException {
        String response = getString(URL);
        return new JSONArray(response);
    }

    private static String getString(String URL) throws IOException {
        java.net.URL url = new URL(URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Content-Type", "application/json");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();
        return response.toString();
    }

}
