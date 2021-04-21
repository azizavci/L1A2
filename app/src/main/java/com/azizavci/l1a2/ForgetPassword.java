package com.azizavci.l1a2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class ForgetPassword extends AppCompatActivity {

    EditText email;
    Button resetPassword;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        initComponents();


    }

    public void initComponents() {
        email = findViewById(R.id.editTextForgotPassword);
        resetPassword = findViewById(R.id.buttonSendResetPassword);
        auth = FirebaseAuth.getInstance();
    }


    public void ResetPassword(View view) {
        String eposta = email.getText().toString().trim();

        if (eposta.isEmpty()) {
            email.setError("Lütfen e postanızı doğru bir biçimde giriniz!");
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(eposta).matches()) {
            email.setError("Lütfen geçerli bir e posta giriniz!");
            email.requestFocus();
            return;
        }

        auth.sendPasswordResetEmail(eposta).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(
                            ForgetPassword.
                                    this, "Lütfen E Posta Hesabınızı Kontrol Ediniz!", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(ForgetPassword.this,
                            "Lütfen E Posta Hesabınızı Doğru Giriniz!",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    public void ClickBack(View view) {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
        finish();
    }


}