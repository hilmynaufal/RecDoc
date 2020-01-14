package com.team7.recdoc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class FullNutrient {
    @SerializedName("attr_id")
    @Expose
    private Integer attrId;
    @SerializedName("value")
    @Expose
    private Double value;

    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

}
