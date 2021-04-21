package com.azizavci.l1a2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AddNewCategory extends AppCompatActivity {

    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;

    EditText etCategoryId;
    EditText etCategoryName;
    EditText etCategoryIntroduction;
    Button btnUploadCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_category);

        initComponents();
        registerEventHandlers();

    }

    private void initComponents() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        etCategoryId=findViewById(R.id.et_category_id);
        etCategoryName=findViewById(R.id.et_category_name);
        etCategoryIntroduction=findViewById(R.id.et_category_introduction);
        btnUploadCategory=findViewById(R.id.btn_upload_category);
    }

    private void registerEventHandlers() {

        btnUploadCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoryId=etCategoryId.getText().toString();
                String categoryName=etCategoryName.getText().toString();
                String categoryIntroduction=etCategoryIntroduction.getText().toString();

                HashMap<String, Object> productData = new HashMap<>();
                productData.put("categoryId", categoryId);
                productData.put("categoryName",categoryName);
                productData.put("categoryIntroduction", categoryIntroduction);
                productData.put("date", FieldValue.serverTimestamp());

                firebaseFirestore.collection("Categories").add(productData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Intent intent = new Intent(AddNewCategory.this, Categories.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddNewCategory.this, e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }


}