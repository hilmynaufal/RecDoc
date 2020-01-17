package com.team7.recdoc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AltMeasure {

    @SerializedName("serving_weight")
    @Expose
    private Double servingWeight;
    @SerializedName("measure")
    @Expose
    private String measure;
    @SerializedName("seq")
    @Expose
    private Integer seq;
    @SerializedName("qty")
    @Expose
    private double qty;

    public Double getServingWeight() {
        return servingWeight;
    }

    public void setServingWeight(Double servingWeight) {
        this.servingWeight = servingWeight;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

}