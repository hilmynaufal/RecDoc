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

    public Stats(double calories_consumed, double calories_burned, double total_calories) {
        this.calories_consumed = calories_consumed;
        this.calories_burned = calories_burned;
        this.total_calories = total_calories;
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
}
