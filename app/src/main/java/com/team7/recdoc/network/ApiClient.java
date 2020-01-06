package com.team7.recdoc.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_API_URL = "https://web-halodoc-api.prod.halodoc.com/";
    private static ApiClient apiClient;
    private Retrofit retrofit;

    private ApiClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpclient = new OkHttpClient.Builder();
        httpclient.addInterceptor(logging);

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpclient.build())
                .build();
    }

    public static synchronized ApiClient getInstance() {
        if (apiClient==null) {
            apiClient = new ApiClient();
        }
        return apiClient;
    }

    public APIService getAPIService() {
        return retrofit.create(APIService.class);
    }
}
