package com.team7.recdoc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.team7.recdoc.R;
import com.team7.recdoc.databinding.ExerciseListBinding;
import com.team7.recdoc.viewmodel.ExerciseListViewModel;

import java.util.ArrayList;

public class ExerciseDataAdapter extends RecyclerView.Adapter<ExerciseDataAdapter.ViewHolder> {

    private ArrayList<ExerciseListViewModel> arrayList;
    private Context context;
    private LayoutInflater layoutInflater;

    public ExerciseDataAdapter(ArrayList<ExerciseListViewModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ExerciseDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ExerciseListBinding exerciseListBinding = DataBindingUtil.inflate(layoutInflater, R.layout.exercise_list, parent, false);

        return new ExerciseDataAdapter.ViewHolder(exerciseListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseDataAdapter.ViewHolder holder, int position) {
        ExerciseListViewModel exerciseListViewModel = arrayList.get(position);
        holder.bind(exerciseListViewModel);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ExerciseListBinding exerciseListBinding;

        public ViewHolder(@NonNull ExerciseListBinding exerciseListBinding) {
            super(exerciseListBinding.getRoot());
            this.exerciseListBinding = exerciseListBinding;
        }

        public void bind(ExerciseListViewModel exceli) {
            this.exerciseListBinding.setExercise(exceli);
            exerciseListBinding.executePendingBindings();
        }

        public ExerciseListBinding getFoodViewBinding() {
            return exerciseListBinding;
        }
    }
}
