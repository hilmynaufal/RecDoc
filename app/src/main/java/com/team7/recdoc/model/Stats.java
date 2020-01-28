package com.team7.recdoc.model;

public class Stats {
    double calories_consumed;
    double calories_burned;
    double total_calories;
    String last_food_consumed;
    String last_exercise;
    String consumed_on;
    String exercise_on;

    public Stats() {
    }

    public Stats(double calories_consumed, double calories_burned, double total_calories, String last_food_consumed, String last_exercise) {
        this.calories_consumed = calories_consumed;
        this.calories_burned = calories_burned;
        this.total_calories = total_calories;
        this.last_food_consumed = last_food_consumed;
        this.last_exercise = last_exercise;
    }

    public double getCalories_consumed() {
        return calories_consumed;
    }

    public void setCalories_consumed(double calories_consumed) {
        this.calories_consumed = calories_consumed;
    }

    public double getCalories_burned() {
        return calories_burned;
    }

    public void setCalories_burned(double calories_burned) {
        this.calories_burned = calories_burned;
    }

    public double getTotal_calories() {
        return total_calories;
    }

    public void setTotal_calories(double total_calories) {
        this.total_calories = total_calories;
    }

    public String getLast_food_consumed() {
        return last_food_consumed;
    }

    public void setLast_food_consumed(String last_food_consumed) {
        this.last_food_consumed = last_food_consumed;
    }

    public String getLast_exercise() {
        return last_exercise;
    }

    public void setLast_exercise(String last_exercise) {
        this.last_exercise = last_exercise;
    }

    public String getConsumed_on() {
        return consumed_on;
    }

    public void setConsumed_on(String consumed_on) {
        this.consumed_on = consumed_on;
    }

    public String getExercise_on() {
        return exercise_on;
    }

    public void setExercise_on(String exercise_on) {
        this.exercise_on = exercise_on;
    }
}
