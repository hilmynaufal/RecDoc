package com.team7.recdoc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.team7.recdoc.R;
import com.team7.recdoc.viewmodel.FoodListViewModel;
import com.team7.recdoc.databinding.FoodListBinding;

import java.util.ArrayList;

public class FoodDataAdapter extends RecyclerView.Adapter<FoodDataAdapter.ViewHolder> {

    private ArrayList<FoodListViewModel> arrayList;
    private Context context;
    private LayoutInflater layoutInflater;

    public FoodDataAdapter(ArrayList<FoodListViewModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public FoodDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        FoodListBinding foodListBinding = DataBindingUtil.inflate(layoutInflater, R.layout.food_list, parent, false);

        return new ViewHolder(foodListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodDataAdapter.ViewHolder holder, int position) {
        FoodListViewModel foodListViewModel = arrayList.get(position);
        holder.bind(foodListViewModel);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private FoodListBinding foodListBinding;

        public ViewHolder(@NonNull FoodListBinding foodListBinding) {
            super(foodListBinding.getRoot());
            this.foodListBinding = foodListBinding;
        }

        public void bind(FoodListViewModel foodli) {
            this.foodListBinding.setFood(foodli);
            foodListBinding.executePendingBindings();
        }

        public FoodListBinding getFoodViewBinding() {
            return foodListBinding;
        }
    }
}
