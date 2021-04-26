package com.azizavci.l1a2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class Products extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    ArrayList<String> userEmailFromFB;
    ArrayList<String> productCommentFromFB;
    ArrayList<String> productImageFromFB;
    ArrayList<String> productNameFromFB;
    //ArrayList<Long> productUnitPriceFromFB;

    ProductRecyclerAdapter productRecyclerAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);


        initComponents();

        getDataFromFireStore();

        RecyclerView recyclerView = findViewById(R.id.products_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productRecyclerAdapter = new ProductRecyclerAdapter(productNameFromFB, productImageFromFB);
        recyclerView.setAdapter(productRecyclerAdapter);

    }

    private void initComponents() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();

        userEmailFromFB = new ArrayList<>();
        productCommentFromFB = new ArrayList<>();
        productImageFromFB = new ArrayList<>();
        productNameFromFB = new ArrayList<>();
        //productUnitPriceFromFB = new ArrayList<>();
    }


    public void clickMenu(View view){
        Intent intent = new Intent(Products.this, MyMenu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void addNewItem(View view) {

        Intent intent = new Intent(Products.this, AddNewProduct.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    public void getDataFromFireStore() {

        CollectionReference collectionReference = firebaseFirestore.collection("Products");

        collectionReference.orderBy("date",Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Toast.makeText(Products.this,error.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                }

                if (value!=null ){
                    for(
                            DocumentSnapshot snapshot :value.getDocuments())

                    {

                        Map<String, Object> data = snapshot.getData();

                        //Casting
                        String comment = (String) data.get("comment");
                        String userEmail = (String) data.get("useremail");
                        String downloadUrl = (String) data.get("downloadurl");
                        String productName = (String) data.get("productname");
                        //long unitPrice = (long) data.get("unitprice");


                        userEmailFromFB.add(userEmail);
                        productCommentFromFB.add(comment);
                        productImageFromFB.add(downloadUrl);
                        productNameFromFB.add(productName);
                        //productUnitPriceFromFB.add(unitPrice);



                        productRecyclerAdapter.notifyDataSetChanged();

                    }
                }
            }
        });
    }
}

