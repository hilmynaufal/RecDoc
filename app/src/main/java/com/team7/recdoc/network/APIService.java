package com.team7.recdoc.network;

import com.team7.recdoc.model.FoodResult;
import com.team7.recdoc.model.Result;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @GET("v4/articles/related/id/18023?limit=5")
    Call<Result> getResultInfo();

    @GET("18023?limit=5")
    Call<ResponseBody> getResultAsJSON();

    @Headers({"Content-Type: application/json", "x-app-id:e06e0485", "x-app-key:b9bff4f40819d47d86474c632aba5673", "x-remote-user-id:0"})
    @POST("v2/natural/nutrients")
    Call<FoodResult> getFoodResult(@Body Request body);
}
