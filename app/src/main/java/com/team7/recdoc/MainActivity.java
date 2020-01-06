package com.team7.recdoc;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.team7.recdoc.adapter.BeritaDataAdapter;
import com.team7.recdoc.viewmodel.BeritaListViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BeritaListViewModel beritaListViewModel;
    private BeritaDataAdapter beritaDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.viewHome);
        beritaListViewModel = ViewModelProviders.of(this).get(BeritaListViewModel.class);
        beritaListViewModel.getMutableLiveData()
                .observe(this, new Observer<ArrayList<BeritaListViewModel>>() {
                    @Override
                    public void onChanged(ArrayList<BeritaListViewModel> beritaListViewModels) {
                        beritaDataAdapter = new BeritaDataAdapter(beritaListViewModels, MainActivity.this);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView.setAdapter(beritaDataAdapter);
                    }
                });
    }
}
