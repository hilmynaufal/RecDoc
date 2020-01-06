package com.team7.recdoc.viewmodel;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.team7.recdoc.model.Content;
import com.team7.recdoc.model.Result;
import com.team7.recdoc.network.APIService;
import com.team7.recdoc.network.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeritaListViewModel extends ViewModel {
    public String id = "";
    public String title = "";
    public String publishDate = "";
    public String photoLandscape = "";
    public MutableLiveData<ArrayList<BeritaListViewModel>> mutableLiveData = new MutableLiveData<>();

    private ArrayList<BeritaListViewModel> arrayList;
    private ArrayList<Content> contents;

    public String getImgUrl() {
        return photoLandscape;
    }

    @BindingAdapter({"imgUrl"})
    public static void loadimage(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(imageView);
    }

    public BeritaListViewModel() {

    }

    public BeritaListViewModel(Content content) {
        this.id = content.getId();
        this.title = content.getTitle();
        this.publishDate = content.getPublishDate();
        this.photoLandscape = content.getPhotoLanscape();
    }

    public MutableLiveData<ArrayList<BeritaListViewModel>> getMutableLiveData() {
        arrayList = new ArrayList<>();

        APIService service = ApiClient.getInstance().getAPIService();
        Call<Result> result = service.getResultInfo();
        result.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                try {
                    contents = new ArrayList<>();
                    contents = response.body().getContent();

                    for (int i = 0; i < contents.size(); i++) {
                        Content cnt = contents.get(i);
                        BeritaListViewModel beritaListViewModel = new BeritaListViewModel(cnt);
                        arrayList.add(beritaListViewModel);
                        mutableLiveData.setValue(arrayList);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
