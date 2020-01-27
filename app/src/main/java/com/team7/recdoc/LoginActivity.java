package com.team7.recdoc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    private SharedPreferences localuser;
    private EditText edt_email;
    private EditText edt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);

        localuser = getSharedPreferences("UserLocal", MODE_PRIVATE);
        mAuth = FirebaseAuth.getInstance();

        Button btn_login = findViewById(R.id.btn_login);

        edt_email = findViewById(R.id.edt_email);
        edt_email.setText(localuser.getString("email", ""));

        edt_password = findViewById(R.id.edt_password);
        edt_password.setText(localuser.getString("password", ""));

        TextView txt_ToSignUp = findViewById(R.id.txt_ToSignUp);

        final String email = edt_email.getText().toString();
        final String password = edt_password.getText().toString();

        progressDialog.setMessage("Logging In...");
        checkingLoggedInUser(email, password);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                final String email = edt_email.getText().toString();
                final String password = edt_password.getText().toString();
                if (email.equals("") || password.equals("")) {
                    Toast.makeText(getBaseContext(), "Username & Password can't be empty!", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                } else fun_auth(email, password);
            }
        });

        txt_ToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startToSignUpActivity();
            }
        });
    }

    void fun_auth(final String email, final String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("cekcek", "sukses");

                            //insert data to local
                            SharedPreferences.Editor editor = localuser.edit();
                            editor.putString("email", email);
                            editor.putString("password", password);
                            editor.apply();
                            //ended

                            startToMainActivity();
                        } else {
                            Log.w("cekcek", task.getException());
                            Toast.makeText(getBaseContext(), "Username or Password is wrong!", Toast.LENGTH_LONG).show();

                            //deleting inserted password from local
                            SharedPreferences.Editor editor = localuser.edit();
                            editor.remove("password");
                            editor.apply();
                            //ended

                            edt_password.setText(localuser.getString("password", ""));
                        }
                    }
                });

    }

    void startToMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    void startToSignUpActivity() {
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
    }

    void checkingLoggedInUser(String email, String password) {
        progressDialog.show();
        if (!(email.equals("") || password.equals(""))) {
            fun_auth(email, password);
        } else progressDialog.dismiss();
    }

}
