package com.ptp.phamtanphat.jsonobject0405;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    Button btnReadJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //https://khoapham.vn/KhoaPhamTraining/json/tien/demo1.json
        btnReadJson = findViewById(R.id.buttonReadJson);

        btnReadJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ReadJson().execute("https://khoapham.vn/KhoaPhamTraining/json/tien/demo3.json");
            }
        });
    }

    class ReadJson extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String ketqua = docNoiDung_Tu_URL(strings[0]);
            return ketqua;
        }

        @Override
        protected void onPostExecute(String s) {
            Xulyjson(s,"vn");
            super.onPostExecute(s);
        }
    }
    private void Xulyjson(String s , String tukhoa) {
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONObject jsonObjectlanguage = jsonObject.getJSONObject("language");

            JSONObject jsonObjectKhoahoc = jsonObjectlanguage.getJSONObject(tukhoa);
            String name = jsonObjectKhoahoc.getString("name");
            String address = jsonObjectKhoahoc.getString("address");
            String course1 = jsonObjectKhoahoc.getString("course1");
            String course2 = jsonObjectKhoahoc.getString("course2");
            String course3 = jsonObjectKhoahoc.getString("course3");

            Log.d("BBB",name + course3);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    private String docNoiDung_Tu_URL(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
