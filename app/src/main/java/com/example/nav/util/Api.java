package com.example.nav.util;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
    @POST("login")
    Call<ResponseBody> loginUser(@Body RequestBody params);

    //Pass this in url
    @POST("4/family")
    Call<ResponseBody> addDependent(@Body RequestBody params);

    @POST("register")
    Call<ResponseBody> registerUser(@Body RequestBody params);

    @GET("1/family")
    Call<Object> getFamilyDetails();
}
