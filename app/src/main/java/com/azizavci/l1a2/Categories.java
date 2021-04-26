package com.azizavci.l1a2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class Categories extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    ArrayList<String> categoryNameFromFB;


    CategoryRecyclerAdapter categoryRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        initComponents();

        getDataFromFireStore();

        RecyclerView recyclerView = findViewById(R.id.categories_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        categoryRecyclerAdapter = new CategoryRecyclerAdapter(categoryNameFromFB);
        recyclerView.setAdapter(categoryRecyclerAdapter);
    }

    private void initComponents() {

        firebaseFirestore=FirebaseFirestore.getInstance();
    }

    public void clickMenu(View view){
        Intent intent = new Intent(Categories.this, MyMenu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void addNewItem(View view) {

        Intent intent = new Intent(Categories.this, AddNewCategory.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    public void getDataFromFireStore() {

        CollectionReference collectionReference = firebaseFirestore.collection("Categories");

        collectionReference.orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Toast.makeText(Categories.this,error.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                }

                if (value!=null ){
                    for(
                            DocumentSnapshot snapshot :value.getDocuments())

                    {

                        Map<String, Object> data = snapshot.getData();

                        //Casting
                        String category = (String) data.get("categoryName");

                        categoryNameFromFB.add(category);




                        categoryRecyclerAdapter.notifyDataSetChanged();

                    }
                }
            }
        });
    }


}