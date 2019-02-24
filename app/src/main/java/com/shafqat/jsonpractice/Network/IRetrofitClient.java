package com.shafqat.jsonpractice.Network;

import com.shafqat.jsonpractice.Model.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Opriday on 12/14/2018.
 */

public interface IRetrofitClient {

    @GET("class-by-teacher/11")
    Call<Response> getResponseFromApi();
}
