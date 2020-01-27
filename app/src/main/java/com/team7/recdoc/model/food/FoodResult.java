package com.team7.recdoc.model.food;


import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FoodResult {

    @SerializedName("foods")
    @Expose
    private ArrayList<Food> foods = null;

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

}
