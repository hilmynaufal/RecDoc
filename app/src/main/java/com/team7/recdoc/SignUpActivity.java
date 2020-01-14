package com.team7.recdoc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    SharedPreferences localuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        localuser = getSharedPreferences("UserLocal", Context.MODE_PRIVATE);

        final EditText edt_emailSignUp = findViewById(R.id.edt_emailSignUp);

        final EditText edt_passwordSignUp = findViewById(R.id.edt_passwordSignUp);

        //final TextView txtReady = findViewById(R.id.txt_Ready);

        Button btnSignup = findViewById(R.id.btn_SignUp);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = edt_emailSignUp.getText().toString();
                final String password = edt_passwordSignUp.getText().toString();
                if (username.equals("") || password.equals("")) {
                    Toast.makeText(getBaseContext(), "Email & Username can't be empty!", Toast.LENGTH_LONG);
                } else fun_signup(username, password);

                //txtReady.setText("Acount has been created! Click Here To Login!");
            }
        });
    }

    void fun_signup(final String uName, final String pWd) {
        mAuth.createUserWithEmailAndPassword(uName, pWd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("cekcek", "creating user success");

                            //insert data to local
                            SharedPreferences.Editor editor = localuser.edit();
                            editor.putString("username", uName);
                            editor.putString("password", pWd);
                            editor.apply();
                            //ended

                            Toast.makeText(SignUpActivity.this, "Account has been created!", Toast.LENGTH_LONG).show();
                            startToLoginActivity();
                        } else {
                            Log.w("cekcek", "failure to create", task.getException());
                            Toast.makeText(SignUpActivity.this, "There's something wrong happened :(", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    void startToLoginActivity() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}
