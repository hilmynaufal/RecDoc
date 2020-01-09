package com.team7.recdoc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.team7.recdoc.R;
import com.team7.recdoc.viewmodel.BeritaListViewModel;
import com.team7.recdoc.databinding.ActivityListBinding;

import java.util.ArrayList;

public class BeritaDataAdapter extends RecyclerView.Adapter<BeritaDataAdapter.ViewHolder> {
    private ArrayList<BeritaListViewModel> arrayList;
    private Context context;
    private LayoutInflater layoutInflater;

    public BeritaDataAdapter(ArrayList<BeritaListViewModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ActivityListBinding activityListBinding = DataBindingUtil.inflate(layoutInflater, R.layout.activity_list, parent, false);

        return new ViewHolder(activityListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        BeritaListViewModel beritaListViewModel = arrayList.get(position);
        holder.bind(beritaListViewModel);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ActivityListBinding activityListBinding;

        public ViewHolder(@NonNull ActivityListBinding activityListBinding) {
            super(activityListBinding.getRoot());
            this.activityListBinding = activityListBinding;
        }

        public void bind(BeritaListViewModel beritali) {
            this.activityListBinding.setBerita(beritali);
            activityListBinding.executePendingBindings();
        }

        public ActivityListBinding getActivityListBinding() {
            return activityListBinding;
        }
    }
}
