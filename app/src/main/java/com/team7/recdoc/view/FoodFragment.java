package com.team7.recdoc.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.team7.recdoc.R;
import com.team7.recdoc.adapter.FoodDataAdapter;
import com.team7.recdoc.network.FirebaseClient;
import com.team7.recdoc.viewmodel.FoodListViewModel;

import java.util.ArrayList;

public class FoodFragment extends Fragment implements View.OnClickListener {


    private RecyclerView recyclerView;
    private FoodListViewModel foodListViewModel;
    private FoodDataAdapter foodDataAdapter;
    private String search;
    private LinearLayout FoodLayout;
    private double total = 0.0;
    private double ttl = 0;

    TextView textView;
    TextView totalcalories;

    @Nullable

    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_lifestyle, container, false);

        textView = view.findViewById(R.id.edtSearchFood);
        FoodLayout = view.findViewById(R.id.llCalories);
        totalcalories = view.findViewById(R.id.TotalCalories);

        Button button = view.findViewById(R.id.btnSearchFood);
        final Button btnConsume = view.findViewById(R.id.btnConsume);

        button.setOnClickListener(this);

        final FirebaseClient client = FirebaseClient.getInstance();
        client.setReference();

        btnConsume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client.updateCalories(total, 0);
                total = 0.0;
                totalcalories.setText("Your food has been consumed and were added to your profile!");
                btnConsume.setVisibility(View.GONE);
            }
        });

        recyclerView = view.findViewById(R.id.viewFood);
        foodListViewModel = ViewModelProviders.of(this).get(FoodListViewModel.class);

        return view;
    }

    @Override
    public void onClick(View v) {
        search = textView.getText().toString();
        foodListViewModel.getMutableLiveData(search)
                .observe(this, new Observer<ArrayList<FoodListViewModel>>() {
                    @Override
                    public void onChanged(ArrayList<FoodListViewModel> foodListViewModels) {
                        foodDataAdapter = new FoodDataAdapter(foodListViewModels, getContext());
                        for (int i = 0; i < foodListViewModels.size(); i++) {
                            ttl += foodListViewModels.get(i).nf_total_calories;
                            total = ttl;
                        }
                        ttl = 0;
                        String sTotal = "Total Calories = " + total + " kcal";
                        totalcalories.setText(sTotal);
                        FoodLayout.setVisibility(View.VISIBLE);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(foodDataAdapter);
                    }
                });
    }
}