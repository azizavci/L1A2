package com.azizavci.l1a2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

public class Categories extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        initComponents();
    }

    private void initComponents() {

        firebaseFirestore=FirebaseFirestore.getInstance();
    }

    public void addNewItem(View view) {

        Intent intent = new Intent(Categories.this, AddNewCategory.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }


}