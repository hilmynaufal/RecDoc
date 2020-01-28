package com.team7.recdoc.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.team7.recdoc.LoginActivity;
import com.team7.recdoc.R;
import com.team7.recdoc.SignUpActivity;
import com.team7.recdoc.model.User;
import com.team7.recdoc.network.FirebaseClient;
import com.team7.recdoc.tools.Delay;

import java.text.DecimalFormat;

import static android.content.Context.MODE_PRIVATE;

public class MoreFragment extends Fragment {



    private ProgressDialog progressDialog;
    private SharedPreferences localuser;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private TextView tvConsumed, tvBurned, tvTotal;

    @Nullable

    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)  {

        View view = inflater.inflate(R.layout.activity_profile, container, false);

        progressDialog = ProgressDialog.show(getContext(), "Updating Profile","Please wait...", true, true);
        Button btnLogout = view.findViewById(R.id.btnLogout);
        final TextView tv_username = view.findViewById(R.id.tv_username);

        tvConsumed = view.findViewById(R.id.tvConsumed);
        tvBurned = view.findViewById(R.id.tvBurned);
        tvTotal = view.findViewById(R.id.tvTotal);

        final FirebaseClient client = FirebaseClient.getInstance();
        client.setReference();

        final DecimalFormat df = new DecimalFormat("#.##");

        Button btnTest = view.findViewById(R.id.btnTest);

        Delay.delay(3, new Delay.DelayCallback() {
            @Override
            public void afterDelay() {
                progressDialog.dismiss();
                progressDialog.cancel();
                tvConsumed.setText(df.format(client.getConsumed()));
                tvBurned.setText(df.format(client.getBurned()));
                tvTotal.setText(df.format(client.getTotal()));
            }
        });

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvConsumed.setText(df.format(client.getConsumed()));
                tvBurned.setText(df.format(client.getBurned()));
                tvTotal.setText(df.format(client.getTotal()));
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
}