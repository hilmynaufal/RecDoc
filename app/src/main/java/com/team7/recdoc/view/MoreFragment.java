package com.team7.recdoc.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.team7.recdoc.LoginActivity;
import com.team7.recdoc.R;
import com.team7.recdoc.network.FirebaseClient;
import com.team7.recdoc.tools.Delay;

import java.text.DecimalFormat;

public class MoreFragment extends Fragment {


    private ProgressDialog progressDialog;
    private SharedPreferences localuser;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private TextView tvConsumed, tvBurned, tvTotal, tvLastExercise, tvLastFood, tvEmail, tvUsername;
    private DecimalFormat df;
    private FirebaseClient profileClient;
    private FirebaseClient statsClient;

    @Nullable

    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_profile, container, false);

        progressDialog = ProgressDialog.show(getContext(), "Updating Profile", "Please wait...", true, true);
        Button btnLogout = view.findViewById(R.id.btnLogout);
        tvUsername = view.findViewById(R.id.tvUsername);


        tvConsumed = view.findViewById(R.id.tvConsumed);
        tvBurned = view.findViewById(R.id.tvBurned);
        tvTotal = view.findViewById(R.id.tvTotal);
        tvLastExercise = view.findViewById(R.id.tvLastExercise);
        tvLastFood = view.findViewById(R.id.tvLastFood);
        tvEmail = view.findViewById(R.id.tvEmail);

        profileClient = FirebaseClient.getInstance();
        profileClient.setReference("profile");

        statsClient = FirebaseClient.getInstance();
        statsClient.setReference("stats");


        df = new DecimalFormat("#.##");

        Button btnReset = view.findViewById(R.id.btnReset);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = ProgressDialog.show(getContext(), "Updating Profile", "Please wait...", true, true);
                statsClient.resetStats();
                loadProfile();
            }
        });

        Delay.delay(3, new Delay.DelayCallback() {
            @Override
            public void afterDelay() {
                loadProfile();
            }
        });

        localuser = this.getActivity().getSharedPreferences("UserLocal", getContext().MODE_PRIVATE);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Logging out");

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();

                //delete user local
                SharedPreferences.Editor editor = localuser.edit();
                editor.clear();
                editor.apply();
                //end

                progressDialog.dismiss();

                startToLoginAvtivity();
            }
        });

        return view;

    }

    void startToLoginAvtivity() {
        Intent i = new Intent(getContext(), LoginActivity.class);
        startActivity(i);
        getActivity().finish();
    }

    void loadProfile() {
        progressDialog.dismiss();
        progressDialog.cancel();
        tvConsumed.setText(df.format(statsClient.getConsumed()));
        tvBurned.setText(df.format(statsClient.getBurned()));
        tvTotal.setText(df.format(statsClient.getTotal()));
        tvEmail.setText(profileClient.getEmail());
        tvUsername.setText(profileClient.getUsername());
        tvLastFood.setText(statsClient.getLastFoodConsumed());
        tvLastExercise.setText(statsClient.getLastExercise());
    }
}