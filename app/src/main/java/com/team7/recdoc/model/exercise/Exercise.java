package com.team7.recdoc.model.exercise;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Exercise {
    @SerializedName("tag_id")
    @Expose
    private Integer tagId;
    @SerializedName("user_input")
    @Expose
    private String userInput;
    @SerializedName("duration_min")
    @Expose
    private Double durationMin;
    @SerializedName("met")
    @Expose
    private Double met;
    @SerializedName("nf_calories")
    @Expose
    private Double nfCalories;
    @SerializedName("photo")
    @Expose
    private Photo photo;
    @SerializedName("compendium_code")
    @Expose
    private Integer compendiumCode;
    @SerializedName("name")
    @Expose
    private String name;

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public Double getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(Double durationMin) {
        this.durationMin = durationMin;
    }

    public Double getMet() {
        return met;
    }

    public void setMet(Double met) {
        this.met = met;
    }

    public Double getNfCalories() {
        return nfCalories;
    }

    public void setNfCalories(Double nfCalories) {
        this.nfCalories = nfCalories;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public Integer getCompendiumCode() {
        return compendiumCode;
    }

    public void setCompendiumCode(Integer compendiumCode) {
        this.compendiumCode = compendiumCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
