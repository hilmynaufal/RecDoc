package com.team7.recdoc.Model;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("v4/articles/related/id/18023?limit=2")
    Call<Result> getResultInfo();

    @GET("18023?limit=5")
    Call<ResponseBody> getResultAsJSON();
}
