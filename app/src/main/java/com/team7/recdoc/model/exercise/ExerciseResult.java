package com.team7.recdoc.model.exercise;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ExerciseResult {
    @SerializedName("exercises")
    @Expose
    private ArrayList<Exercise> exercises = null;

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }

}
