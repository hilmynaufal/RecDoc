package com.team7.recdoc.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.team7.recdoc.model.Content;
import com.team7.recdoc.model.Fields;
import com.team7.recdoc.model.FoodResult;
import com.team7.recdoc.model.Hit;
import com.team7.recdoc.network.APIService;
import com.team7.recdoc.network.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodListViewModel extends ViewModel {

    public String item_name = "";
    public String brand_name = "";
    public int nf_calories = 0;
    public int nf_total_fat = 0;
    public MutableLiveData<ArrayList<FoodListViewModel>> mutableLiveData = new MutableLiveData<>();

    private ArrayList<FoodListViewModel> arrayList;
    private ArrayList<Hit> foods;

    public FoodListViewModel() {

    }

    public FoodListViewModel(Hit hit) {
        this.item_name = hit.getFields().getItemName();
        this.brand_name = hit.getFields().getBrandName();
        this.nf_calories = hit.getFields().getNfCalories();
        this.nf_total_fat = hit.getFields().getNfTotalFat();
    }

    public MutableLiveData<ArrayList<FoodListViewModel>> getMutableLiveData() {
        arrayList = new ArrayList<>();

        APIService service = ApiClient.getFoodInstance().getAPIService();
        Call<FoodResult> result = service.getFoodResult();

        result.enqueue(new Callback<FoodResult>() {
            @Override
            public void onResponse(Call<FoodResult> call, Response<FoodResult> response) {
                try {
                    foods = new ArrayList<>();
                    foods = response.body().getHits();

                    for (int i = 0; i < foods.size(); i++) {
                        Hit hit = foods.get(i);
                        FoodListViewModel foodListViewModel = new FoodListViewModel(hit);
                        arrayList.add(foodListViewModel);
                        mutableLiveData.setValue(arrayList);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<FoodResult> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
