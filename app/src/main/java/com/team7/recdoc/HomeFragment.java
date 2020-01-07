package com.team7.recdoc;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.team7.recdoc.Model.APIService;
import com.team7.recdoc.Model.Content;
import com.team7.recdoc.Model.Result;

import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment {


    public static final String BASE_API_URL = "https://web-halodoc-api.prod.halodoc.com/";
    private ListView listView;
    private List<Content> content;
    private ProgressDialog loading;

    @Nullable
    @Override
    public View onCreateView(@Nonnull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        listView = view.findViewById(R.id.list_test);
        loading = ProgressDialog.show(view.getContext(), "Fetching data...", "please wait", false, false);
        loadjson();
        return view;
    }

    private void loadjson() {


        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpclient = new OkHttpClient.Builder();
        httpclient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpclient.build())
                .build();

        APIService apiService = retrofit.create(APIService.class);
        Call<Result> result = apiService.getResultInfo();
        result.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                try {
                    loading.dismiss();
                    content = response.body().getContent();
                    showList();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    private void showList() {
        String[] items = new String[content.size()];
        for (int i = 0; i < content.size(); i++) {
            items[i] = URLDecoder.decode(content.get(i).getTitle());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter<>(getView().getContext(), R.layout.activity_list, items);
        listView.setAdapter(arrayAdapter);
    }
}