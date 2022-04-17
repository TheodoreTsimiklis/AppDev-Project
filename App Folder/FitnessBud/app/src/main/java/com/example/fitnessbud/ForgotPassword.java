package com.example.fitnessbud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private EditText email;
    private Button resetPass;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email = findViewById(R.id.email);
        resetPass = findViewById(R.id.resetPassword);

        auth = FirebaseAuth.getInstance();

        resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });
    }

    private void resetPassword() {
        String userEmail = email.getText().toString().trim();

        if (userEmail.isEmpty()) {
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            email.setError("Please provide a valid email");
            email.requestFocus();
            return;
        }

        auth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ForgotPassword.this, "An email has been sent to reset Password", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ForgotPassword.this, "An error has occured. Try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}