package com.team7.recdoc;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        final EditText edt_usernameSignUp = findViewById(R.id.edt_usernameSignUp);

        final EditText edt_passwordSignUp = findViewById(R.id.edt_passwordSignUp);

        //final TextView txtReady = findViewById(R.id.txt_Ready);

        Button btnSignup = findViewById(R.id.btn_SignUp);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = edt_usernameSignUp.getText().toString();
                final String password = edt_passwordSignUp.getText().toString();
                fun_signup(username, password);

                //txtReady.setText("Acount has been created! Click Here To Login!");
            }
        });
    }

    void fun_signup(String uName, String pWd) {
        mAuth.createUserWithEmailAndPassword(uName, pWd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("cekcek", "creating user success");
                        } else {
                            Log.w("cekcek", "failure to create", task.getException());
                            Toast.makeText(SignUpActivity.this, "Auth failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    void startToLoginActivity() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}
