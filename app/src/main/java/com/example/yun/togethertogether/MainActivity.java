package com.example.yun.togethertogether;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {
    LinearLayout myLayout;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(" TOTO");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myLayout=(LinearLayout)findViewById(R.id.myLayout);
        animationDrawable=(AnimationDrawable)myLayout.getBackground();
        animationDrawable.setEnterFadeDuration(4500);
        animationDrawable.setExitFadeDuration(4500);
        animationDrawable.start();


        TabHost tabHost = (TabHost)findViewById(R.id.host);
        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tab01");
        tabSpec.setIndicator("HOME",null);
        tabSpec.setContent(R.id.tab1);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tab02");
        tabSpec.setIndicator("THREE",null);
        tabSpec.setContent(R.id.tab2);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tab03");
        tabSpec.setIndicator("FOUR",null);
        tabSpec.setContent(R.id.tab3);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tab04");
        tabSpec.setIndicator("더보기",null);
        tabSpec.setContent(R.id.tab4);
        tabHost.addTab(tabSpec);
    }

}