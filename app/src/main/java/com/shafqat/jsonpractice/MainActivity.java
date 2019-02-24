package com.shafqat.jsonpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import com.shafqat.jsonpractice.Adpater.CustomSpinnerAdapter;
import com.shafqat.jsonpractice.Adpater.ListViewAdapter;
import com.shafqat.jsonpractice.Model.ClassYear;
import com.shafqat.jsonpractice.Model.Data;
import com.shafqat.jsonpractice.Model.Response;
import com.shafqat.jsonpractice.Network.IRetrofitClient;
import com.shafqat.jsonpractice.Network.RetrofitConstant;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Spinner spinner;
    ListView listView;
    IRetrofitClient retrofitClient;
    List<String> keys = new ArrayList<>();
    Data data;
    CustomSpinnerAdapter adapter;
    ListViewAdapter listAdapter;
    List<ClassYear> getClassDataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.spinner);
        listView = (ListView) findViewById(R.id.listView);
        listAdapter = new ListViewAdapter(MainActivity.this,R.layout.listview_item,getClassDataList);
        listView.setAdapter(listAdapter);
        spinner.setOnItemSelectedListener(this);
        retrofitClient = RetrofitConstant.getRetrofitClient();
        CallApi();
    }

    private void CallApi() {

        retrofitClient.getResponseFromApi().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Response res = response.body();
                if (res != null){

                    keys = res.getKeys();
                    data = res.getData();
                    adapter = new CustomSpinnerAdapter(keys,MainActivity.this);
                    spinner.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });

    }

    // spinner click listener.

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (keys.get(position).contentEquals("1st-year")){
            getClassDataList = data.get1stYear();
            listAdapter.setData(getClassDataList);
        }else if (keys.get(position).contentEquals("2nd-Year-A")){
            getClassDataList = data.get2ndYear();
            listAdapter.setData(getClassDataList);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
