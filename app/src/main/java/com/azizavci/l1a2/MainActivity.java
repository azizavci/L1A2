package com.azizavci.l1a2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import Entities.Order;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN=5000;
    //variables
    Animation topAnimation;
    Animation bottomAnimation;
    ImageView logo;
    TextView designedBy, year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        topAnimation= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnimation=AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //hooks
        logo=findViewById(R.id.logo);
        designedBy=findViewById(R.id.designedBy);
        year=findViewById(R.id.year);

        logo.setAnimation(topAnimation);
        designedBy.setAnimation(bottomAnimation);
        year.setAnimation(bottomAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,HomePage.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);



    }
}