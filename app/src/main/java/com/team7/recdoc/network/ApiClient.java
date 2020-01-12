package com.team7.recdoc.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static ApiClient apiClient;
    private Retrofit retrofit;

    private ApiClient() {
        final String BASE_API_URL = "https://web-halodoc-api.prod.halodoc.com/";

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpclient = new OkHttpClient.Builder();
        httpclient.addInterceptor(logging);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpclient.build())
                .build();
    }

    public ApiClient(int i) {
        final String BASE_API_URL = "https://nutritionix-api.p.rapidapi.com/";

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpclient = new OkHttpClient.Builder();
        httpclient.addInterceptor(logging);

        httpclient.networkInterceptors();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpclient.build())
                .build();
    }

    public static synchronized ApiClient getInstance() {
        apiClient = new ApiClient();
        return apiClient;
    }

    public static synchronized ApiClient getFoodInstance() {
        apiClient = new ApiClient(1);
        return apiClient;
    }

    public APIService getAPIService() {
        return retrofit.create(APIService.class);
    }
}
