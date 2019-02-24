package com.shafqat.jsonpractice.Network;

/**
 * Created by Opriday on 12/14/2018.
 */

public class RetrofitConstant {

    public static String BASE_URL = "http://www.googlelabz.com/ptc/api/";
    public static IRetrofitClient getRetrofitClient(){
        return RetrofitClient.getClient(BASE_URL).create(IRetrofitClient.class);
    }
}
