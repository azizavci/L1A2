package com.azizavci.l1a2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import android.view.View;

public class HomePage extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Functions functions = new Functions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        initComponents();
    }

    private void initComponents() {
        drawerLayout = findViewById(R.id.drawer_layout_home);
    }

    public void clickMenu(View view) {
        functions.openDrawer(drawerLayout);
    }

    public void clickLogo(View view) {
        functions.closeDrawer(drawerLayout);
    }

    public void clickHome(View view) {
        functions.closeDrawer(drawerLayout);
    }

    public void clickDashBoard(View view) {
        //redirect activity to dashboard
        functions.redirectActivity(this, Dashboard.class);
    }

    public void clickAboutUs(View view) {
        //redirect activity to about us
        functions.redirectActivity(this, AboutUs.class);
    }

    public void clickLogin(View view) {
        functions.redirectActivity(this, Account.class);
    }

    public void clickLogout(View view) {
        //close app
        functions.LogOut(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        functions.closeDrawer(drawerLayout);
    }
}