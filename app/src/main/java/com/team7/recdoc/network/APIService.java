package com.team7.recdoc.network;

import com.team7.recdoc.model.FoodResult;
import com.team7.recdoc.model.Result;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface APIService {
    @GET("v4/articles/related/id/18023?limit=5")
    Call<Result> getResultInfo();

    @GET("18023?limit=5")
    Call<ResponseBody> getResultAsJSON();

    @Headers({"x-rapidapi-host: nutritionix-api.p.rapidapi.com", "x-rapidapi-key: 98c559335dmsh865aec8955d97e4p10966cjsn038b15348d12"})
    @GET("v1_1/search/cheddar%2520cheese?fields=item_name%252Citem_id%252Cbrand_name%252Cnf_calories%252Cnf_total_fat")
    Call<FoodResult> getFoodResult();
}
