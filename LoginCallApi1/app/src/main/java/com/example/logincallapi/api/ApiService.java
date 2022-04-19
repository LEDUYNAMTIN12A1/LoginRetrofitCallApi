package com.example.logincallapi.api;

import com.example.logincallapi.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

import com.example.logincallapi.Result;

public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ApiService apiservice = new Retrofit.Builder()
            .baseUrl(" https://bmis.vnpt.vn")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);



    @POST("/cudanapi/api/Mobile/DangNhap")
    Call<Result> senduser(@Body User user);
}
