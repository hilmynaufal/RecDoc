package com.team7.recdoc;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        Button btn_login = findViewById(R.id.btn_login);

        final EditText edt_email = findViewById(R.id.edt_email);

        final EditText edt_password = findViewById(R.id.edt_password);

        TextView txt_ToSignUp = findViewById(R.id.txt_ToSignUp);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = edt_email.getText().toString();
                final String password = edt_password.getText().toString();
                if (username.equals("") || password.equals("")) {
                    Toast.makeText(getBaseContext(), "Username & Password can't be empty!", Toast.LENGTH_LONG).show();
                } else fun_auth(username, password);
            }
        });

        txt_ToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startToSignUpActivity();
            }
        });
    }

    void fun_auth(final String username, final String password) {
        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("cekcek", "sukses");
                            startToMainActivity();
                        } else {
                            Log.w("cekcek", task.getException());
                            Toast.makeText(getBaseContext(), "Username or Password is wrong!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    void startToMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    void startToSignUpActivity() {
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
    }
}
