package com.team7.recdoc.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.team7.recdoc.model.exercise.Exercise;
import com.team7.recdoc.model.exercise.ExerciseResult;
import com.team7.recdoc.model.food.Food;
import com.team7.recdoc.network.APIService;
import com.team7.recdoc.network.ApiClient;
import com.team7.recdoc.network.ExerciseRequest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExerciseListViewModel extends ViewModel {

    public String exerciseName = "";
    public String MET = "";
    public String duration = "";
    public String calories = "";
    public double total_calories = 0.0;

    public MutableLiveData<ArrayList<ExerciseListViewModel>> mutableLiveData = new MutableLiveData<>();

    private ArrayList<ExerciseListViewModel> arrayList;
    private ArrayList<Exercise> exercises;

    public ExerciseListViewModel() {

    }

    public ExerciseListViewModel(Exercise exercise) {
        this.exerciseName = exercise.getName();
        this.MET = exercise.getMet().toString();
        this.duration = exercise.getDurationMin().toString();
        this.calories = exercise.getNfCalories().toString();
        this.total_calories = exercise.getNfCalories();
    }

    public MutableLiveData<ArrayList<ExerciseListViewModel>> getMutableLiveData(String s) {
        arrayList = new ArrayList<>();

        APIService service = ApiClient.getNutritionInstance().getAPIService();
        final Call<ExerciseResult> result = service.getExerciseResult(new ExerciseRequest(s));

        result.enqueue(new Callback<ExerciseResult>() {
            @Override
            public void onResponse(Call<ExerciseResult> call, Response<ExerciseResult> response) {
                try {
                    exercises = new ArrayList<>();
                    exercises = response.body().getExercises();

                    Log.d("cekcek", "exercise berhasil");

                    for (int i = 0; i < exercises.size(); i++) {
                        Exercise exercise = exercises.get(i);
                        ExerciseListViewModel exerciseListViewModel= new ExerciseListViewModel(exercise);
                        arrayList.add(exerciseListViewModel);
                        mutableLiveData.setValue(arrayList);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ExerciseResult> call, Throwable t) {
                t.printStackTrace();
            }
        });
        if (mutableLiveData != null) Log.d("cekcek", "tdk error");
        return mutableLiveData;
    }
}
