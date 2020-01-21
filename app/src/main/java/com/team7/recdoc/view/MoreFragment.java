package com.team7.recdoc.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.team7.recdoc.LoginActivity;
import com.team7.recdoc.R;
import com.team7.recdoc.SignUpActivity;

import static android.content.Context.MODE_PRIVATE;

public class MoreFragment extends Fragment {



    private ProgressDialog progressDialog;
    private SharedPreferences localuser;

    @Nullable

    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)  {

        View view = inflater.inflate(R.layout.activity_profile, container, false);

        Button btnLogout = view.findViewById(R.id.btnLogout);

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