package com.team7.recdoc.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.team7.recdoc.model.Food;
import com.team7.recdoc.model.FoodResult;
import com.team7.recdoc.network.APIService;
import com.team7.recdoc.network.ApiClient;
import com.team7.recdoc.network.Request;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodListViewModel extends ViewModel {

    public String food_name = "";
    public String brand_name = "";
    public String nf_calories = "";
    public String nf_total_fat = "";
    public MutableLiveData<ArrayList<FoodListViewModel>> mutableLiveData = new MutableLiveData<>();

    private ArrayList<FoodListViewModel> arrayList;
    private ArrayList<Food> foods;

    public FoodListViewModel() {

    }

    public FoodListViewModel(Food food) {
        this.food_name = food.getFoodName();
    }

    public MutableLiveData<ArrayList<FoodListViewModel>> getMutableLiveData(String s) {
        arrayList = new ArrayList<>();

        APIService service = ApiClient.getFoodInstance().getAPIService();
        Call<FoodResult> result = service.getFoodResult(new Request(s));

        result.enqueue(new Callback<FoodResult>() {
            @Override
            public void onResponse(Call<FoodResult> call, Response<FoodResult> response) {
                try {
                    foods = new ArrayList<>();
                    foods = response.body().getFoods();

                    Log.d("cekcek", "kontol");

                    for (int i = 0; i < foods.size(); i++) {
                        Food food = foods.get(i);
                        FoodListViewModel foodListViewModel = new FoodListViewModel(food);
                        arrayList.add(foodListViewModel);
                        mutableLiveData.setValue(arrayList);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<FoodResult> call, Throwable t) {
                t.printStackTrace();
            }
        });
        if (mutableLiveData != null) Log.d("cekcek", "tdk error");
        return mutableLiveData;
    }

}
