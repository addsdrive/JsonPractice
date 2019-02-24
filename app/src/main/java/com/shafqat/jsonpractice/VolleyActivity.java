package com.shafqat.jsonpractice;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.shafqat.jsonpractice.Adpater.CustomSpinnerAdapter;
import com.shafqat.jsonpractice.Adpater.ListViewAdapter;
import com.shafqat.jsonpractice.Model.ClassYear;
import com.shafqat.jsonpractice.Model.Data;
import com.shafqat.jsonpractice.Network.IRetrofitClient;
import com.shafqat.jsonpractice.Network.RetrofitConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VolleyActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    ListView listView;
    Data data;
    CustomSpinnerAdapter adapter;
    ListViewAdapter listAdapter;
    List<String> keys = new ArrayList<>();
    List<ClassYear> getClassDataList = new ArrayList<>();
    JSONObject dataObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        spinner = (Spinner) findViewById(R.id.vspinner);
        listView = (ListView) findViewById(R.id.vlistView);
        spinner.setOnItemSelectedListener(this);
        CallApiUsingVolleyLib();
    }

    private void CallApiUsingVolleyLib() {

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, "http://www.googlelabz.com/ptc/api/class-by-teacher/11", null,
                new Response.Listener<JSONObject>() {
                    @TargetApi(Build.VERSION_CODES.KITKAT)
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response != null) {
                            try {
                                JSONObject jsonObj = new JSONObject(response.toString());
                                //First we get the keys list and pass to spinner adapter.
                                JSONArray keyArray = jsonObj.getJSONArray("keys");
                                //Second get the data JsonObject and store it in seprate variable because later will need that object, When user click on item then get class data from data with the help of key.
                                setDataObj(jsonObj.getJSONObject("data"));
                                CreateListAndPassToSpinner(keyArray);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsObjRequest);
    }

    private void CreateListAndPassToSpinner(JSONArray keyArray) throws JSONException {
        for (int i = 0; i < keyArray.length(); i++) {
            String key = keyArray.getString(i);
            keys.add(key);
        }
        adapter = new CustomSpinnerAdapter(keys, VolleyActivity.this);
        spinner.setAdapter(adapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // it will remove the old data from list and add new data to the list.
        getClassDataList.clear();
        if (dataObj != null) {
            try {
                //Remember dataObj is JSONObject and has "data" sotred. In "data" we have classes and their data.
                //Keys list has stored "keys" data from the response. when user click on the item in spinner what I am doing is getting position of the item then get the title of the item from keys list. which will give the title e-g "1st-year or 2nd-year"
                JSONArray classDataArray = dataObj.getJSONArray(keys.get(position));
                // once we get the array then start a looop on it and make a list of data then pass it to the adapter.
                for (int i = 0; i < classDataArray.length(); i++) {
                    JSONObject obj = classDataArray.getJSONObject(i);
                    //Class year is model class which has all the fields of "data","1st/2nd-year".
                    ClassYear classYear = new ClassYear();
                    classYear.setStudentId(obj.getString("student_id"));
                    classYear.setParentId(obj.getString("parent_id"));
                    classYear.setName(obj.getString("name"));
                    classYear.setPhone(obj.getString("phone"));
                    classYear.setAddress(obj.getString("address"));
                    getClassDataList.add(classYear);
                }
                if (getClassDataList != null && getClassDataList.size() > 0) {
                    listAdapter = new ListViewAdapter(VolleyActivity.this, R.layout.listview_item, getClassDataList);
                    listView.setAdapter(listAdapter);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void setDataObj(JSONObject dataObj) {
        this.dataObj = dataObj;
    }
}
