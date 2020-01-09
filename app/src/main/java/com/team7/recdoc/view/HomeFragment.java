package com.team7.recdoc.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.team7.recdoc.R;
import com.team7.recdoc.adapter.BeritaDataAdapter;
import com.team7.recdoc.viewmodel.BeritaListViewModel;

import java.util.ArrayList;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


public class HomeFragment extends Fragment {


    private RecyclerView recyclerView;
    private BeritaListViewModel beritaListViewModel;
    private BeritaDataAdapter beritaDataAdapter;

    @Nullable
    @Override
    public View onCreateView(@Nonnull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.viewHome);
        beritaListViewModel = ViewModelProviders.of(this).get(BeritaListViewModel.class);
        beritaListViewModel.getMutableLiveData()
                .observe(this, new Observer<ArrayList<BeritaListViewModel>>() {
                    @Override
                    public void onChanged(ArrayList<BeritaListViewModel> beritaListViewModels) {
                        beritaDataAdapter = new BeritaDataAdapter(beritaListViewModels, getContext());
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(beritaDataAdapter);
                    }
                });
        return view;
    }
}