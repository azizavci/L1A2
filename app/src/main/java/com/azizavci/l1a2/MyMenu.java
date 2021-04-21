package com.azizavci.l1a2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MyMenu extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_menu);

        initComponents();
    }

    private void initComponents() {
    }

    public void toProductsPage(View view){
        Intent intent = new Intent(this, Products.class);
        startActivity(intent);
        finish();
    }

    public void toCategoriesPage(View view){
        Intent intent = new Intent(this, Categories.class);
        startActivity(intent);
        finish();
    }
}