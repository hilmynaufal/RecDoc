package com.team7.recdoc.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.team7.recdoc.R;
import com.team7.recdoc.adapter.FoodDataAdapter;
import com.team7.recdoc.viewmodel.FoodListViewModel;

import java.util.ArrayList;

public class LifestyleFragment extends Fragment {


    private RecyclerView recyclerView;
    private FoodListViewModel foodListViewModel;
    private FoodDataAdapter foodDataAdapter;

    @Nullable

    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lifestyle, container, false);

        recyclerView = view.findViewById(R.id.viewFood);
        foodListViewModel = ViewModelProviders.of(this).get(FoodListViewModel.class);
        foodListViewModel.getMutableLiveData()
                .observe(this, new Observer<ArrayList<FoodListViewModel>>() {
                    @Override
                    public void onChanged(ArrayList<FoodListViewModel> foodListViewModels) {
                        foodDataAdapter = new FoodDataAdapter(foodListViewModels, getContext());
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(foodDataAdapter);
                    }
                });
        return view;

    }

}