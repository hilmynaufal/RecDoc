package com.team7.recdoc.model.food;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fields {
    @SerializedName("item_name")
    @Expose
    private String itemName;
    @SerializedName("brand_name")
    @Expose
    private String brandName;
    @SerializedName("nf_calories")
    @Expose
    private double nfCalories;
    @SerializedName("nf_total_fat")
    @Expose
    private double nfTotalFat;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public double getNfCalories() {
        return nfCalories;
    }

    public void setNfCalories(double nfCalories) {
        this.nfCalories = nfCalories;
    }

    public double getNfTotalFat() {
        return nfTotalFat;
    }

    public void setNfTotalFat(double nfTotalFat) {
        this.nfTotalFat = nfTotalFat;
    }
}
