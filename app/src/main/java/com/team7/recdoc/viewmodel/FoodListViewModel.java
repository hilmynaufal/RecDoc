package com.team7.recdoc.viewmodel;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.team7.recdoc.model.food.Food;
import com.team7.recdoc.model.food.FoodResult;
import com.team7.recdoc.network.APIService;
import com.team7.recdoc.network.ApiClient;
import com.team7.recdoc.network.FoodRequest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodListViewModel extends ViewModel {

    public String food_name = "";
    public String brand_name = "";
    public String serving_qty = "";
    public String nf_calories = "";
    public double nf_total_calories = 0.0;
    public String thumb = "";
    public MutableLiveData<ArrayList<FoodListViewModel>> mutableLiveData = new MutableLiveData<>();

    private ArrayList<FoodListViewModel> arrayList;
    private ArrayList<Food> foods;

    public String getImgUrl() {
        return thumb;
    }

    @BindingAdapter({"imgUrl"})
    public static void loadimage(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .apply(RequestOptions.overrideOf(250))
                .into(imageView);
    }

    public FoodListViewModel() {

    }

    public FoodListViewModel(Food food) {
        this.food_name = food.getFoodName();
        this.nf_calories = food.getNfCalories().toString() + " kcal";
        this.serving_qty = food.getServingQty().toString();
        this.thumb = food.getPhoto().getThumb();
        this.nf_total_calories = food.getNfCalories();
    }

    public MutableLiveData<ArrayList<FoodListViewModel>> getMutableLiveData(String s) {
        arrayList = new ArrayList<>();

        APIService service = ApiClient.getNutritionInstance().getAPIService();
        Call<FoodResult> result = service.getFoodResult(new FoodRequest(s));

        result.enqueue(new Callback<FoodResult>() {
            @Override
            public void onResponse(Call<FoodResult> call, Response<FoodResult> response) {
                try {
                    foods = new ArrayList<>();
                    foods = response.body().getFoods();

                    Log.d("cekcek", "berhasil");

                    for (int i = 0; i < foods.size(); i++) {
                        Food food = foods.get(i);
                        FoodListViewModel foodListViewModel = new FoodListViewModel(food);
                        arrayList.add(foodListViewModel);
                        mutableLiveData.setValue(arrayList);
                        Log.d("cekcek", "value of nf_total in local = " + food.getNfCalories());
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
