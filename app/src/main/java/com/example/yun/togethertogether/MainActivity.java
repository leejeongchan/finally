package com.example.yun.togethertogether;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends FragmentActivity  {
    FragmentTabHost tabHost;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        setTitle(" TOTO");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tabHost = (FragmentTabHost)findViewById(R.id.tabhost);
        tabHost.setup(this,getSupportFragmentManager(),R.id.fragment_container);
        tabHost.addTab(tabHost.newTabSpec("tab01").setIndicator("Home"),FragmentHome.class,null);
        tabHost.addTab(tabHost.newTabSpec("tab02").setIndicator("Chat"),FragmentChat.class,null);
        tabHost.addTab(tabHost.newTabSpec("tab03").setIndicator("Mypage"),FragmentPost.class,null);
        tabHost.addTab(tabHost.newTabSpec("tab04").setIndicator("more"),FragmentMore.class,null);



    }

}

