package com.example.yun.togethertogether;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toolbar;


public class MainActivity extends AppCompatActivity {
    FragmentTabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        setTitle(" TOTO");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager=(ViewPager)findViewById(R.id.pager);
        MyPagerAdapter adapter=new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setCurrentItem(0);
        TabLayout tabLayout=(TabLayout)findViewById(R.id.tabhost);
        tabLayout.setupWithViewPager(viewPager);


//        tabLayout.addTab(tabLayout.newTab().setText("1"));
//        tabLayout.addTab(tabLayout.newTab().setText("2"));
//        tabLayout.addTab(tabLayout.newTab().setText("3"));



//        tabHost = (FragmentTabHost)findViewById(R.id.tabhost);
//        tabHost.setup(this,getSupportFragmentManager(),R.id.fragment_container);
//        tabHost.addTab(tabHost.newTabSpec("tab01").setIndicator("Home"),FragmentHome.class,null);
//        tabHost.addTab(tabHost.newTabSpec("tab02").setIndicator("Chat"),FragmentChat.class,null);
//        tabHost.addTab(tabHost.newTabSpec("tab03").setIndicator("Mypage"),FragmentPost.class,null);
//        tabHost.addTab(tabHost.newTabSpec("tab04").setIndicator("more"),FragmentMore.class,null);



    }






}



