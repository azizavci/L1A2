package com.azizavci.l1a2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpPage extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    EditText emailText, passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth=FirebaseAuth.getInstance();

        emailText=findViewById(R.id.editTextEmail);
        passwordText=findViewById(R.id.editTextPassword);
    }

    //KAYDOL
    public void SignUpClicked(View view) {

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(SignUpPage.this, "KULLANICI KAYDI BAŞARILI", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignUpPage.this, "KULLANICI KAYDI BAŞARISIZ", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void ClickBack(View view) {
        Intent intent = new Intent(this, Account.class);
        startActivity(intent);
        finish();
    }
}