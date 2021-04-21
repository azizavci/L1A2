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

public class LoginPage extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    EditText emailText, passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        initComponents();

    }

    public void initComponents(){
        firebaseAuth=FirebaseAuth.getInstance();
        emailText=findViewById(R.id.editTextEmail);
        passwordText=findViewById(R.id.editTextPassword);
    }

    public void ClickBack(View view) {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
        finish();
    }
    //GİRİŞ YAP
    public void LoginClicked(View view){

        String email=emailText.getText().toString();
        String password=passwordText.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(LoginPage.this,"KULLANICI GİRİŞİ BAŞARILI",Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginPage.this,"KULLANICI GİRİŞİ BAŞARISIZ",Toast.LENGTH_LONG).show();
            }
        });
    }
}