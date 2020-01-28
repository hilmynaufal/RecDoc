package com.team7.recdoc.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.team7.recdoc.R;
import com.team7.recdoc.adapter.ExerciseDataAdapter;
import com.team7.recdoc.adapter.FoodDataAdapter;
import com.team7.recdoc.network.FirebaseClient;
import com.team7.recdoc.viewmodel.ExerciseListViewModel;
import com.team7.recdoc.viewmodel.FoodListViewModel;

import java.util.ArrayList;

public class ExerciseFragment extends Fragment implements View.OnClickListener {


    private RecyclerView recyclerView;
    private ExerciseListViewModel exerciseListViewModel;
    private ExerciseDataAdapter exerciseDataAdapter;
    private String search;
    private LinearLayout ExcerciseLayout;
    private double ttl;
    private double total;
    private TextView textView;
    private TextView tvTotalCaloriesBurned;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_exercise, container, false);

        textView = view.findViewById(R.id.edtSearchExercise);
        tvTotalCaloriesBurned = view.findViewById(R.id.TotalCaloriesBurned);

        final Button btnDoExercise = view.findViewById(R.id.btnDoExercise);
        ExcerciseLayout = view.findViewById(R.id.llExercise);

        final FirebaseClient client = FirebaseClient.getInstance();
        client.setReference("stats");

        btnDoExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client.updateCalories(0, total, "", search);
                total = 0.0;
                tvTotalCaloriesBurned.setText("Your exercise has been recorded and were added to your profile!");
                btnDoExercise.setVisibility(View.GONE);
            }
        });

        Button button = view.findViewById(R.id.btnSearchExercise);
        button.setOnClickListener(this);

        recyclerView = view.findViewById(R.id.viewExercise);
        exerciseListViewModel = ViewModelProviders.of(this).get(ExerciseListViewModel.class);

        return view;

    }

    @Override
    public void onClick(View v) {
        search = textView.getText().toString();
        exerciseListViewModel.getMutableLiveData(search)
                .observe(this, new Observer<ArrayList<ExerciseListViewModel>>() {
                    @Override
                    public void onChanged(ArrayList<ExerciseListViewModel> exerciseListViewModels) {
                        exerciseDataAdapter = new ExerciseDataAdapter(exerciseListViewModels, getContext());
                        for (int i = 0; i < exerciseListViewModels.size(); i++) {
                            ttl += exerciseListViewModels.get(i).total_calories;
                            total = ttl;
                        }
                        ttl = 0;
                        String sTotal = "Total Calories Burned = " + total + " kcal";
                        tvTotalCaloriesBurned.setText(sTotal);
                        ExcerciseLayout.setVisibility(View.VISIBLE);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(exerciseDataAdapter);
                    }
                });

    }
}