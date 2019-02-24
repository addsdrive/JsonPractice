package com.shafqat.jsonpractice;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        Button volley = (Button) findViewById(R.id.Volley_lib);
        Button retrofit = (Button) findViewById(R.id.retrofit_lib);
        volley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volley = new Intent(WelcomeScreen.this,VolleyActivity.class);
                startActivity(volley);
            }
        });
        
        retrofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(WelcomeScreen.this,MainActivity.class);
                startActivity(main);
            }
        });
    }



}
