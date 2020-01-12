package com.team7.recdoc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FoodResult {
    @SerializedName("total_hits")
    @Expose
    private Integer totalHits;
    @SerializedName("max_score")
    @Expose
    private Double maxScore;
    @SerializedName("hits")
    @Expose
    private ArrayList<Hit> hits = new ArrayList<>();

    public Integer getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(Integer totalHits) {
        this.totalHits = totalHits;
    }

    public Double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Double maxScore) {
        this.maxScore = maxScore;
    }

    public ArrayList<Hit> getHits() {
        return hits;
    }

    public void setHits(ArrayList<Hit> hits) {
        this.hits = hits;
    }

}
