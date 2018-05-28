package com.example.yun.togethertogether;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;


public class Friendfrag extends Fragment {
    RecyclerView mRecyclerView;

    private StorageReference mStorageRef;
    LinearLayoutManager mLayoutManager;
    String stEm;
    List<Friend> mFriend;
    FirebaseDatabase database;
    FriendAdapter mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_friendfrag, container, false);

        mRecyclerView = (RecyclerView)v.findViewById(R.id.frienlist);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mFriend=new ArrayList<>();
        // specify an adapter (see also next example)
        mAdapter = new FriendAdapter(mFriend,stEm);
        mRecyclerView.setAdapter(mAdapter);

        database=FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference("Users");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Friend comment = dataSnapshot.getValue(Friend.class);

                // [START_EXCLUDE]
                // Update RecyclerView
                mFriend.add(comment);
                mAdapter.notifyItemInserted(mFriend.size() - 1);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });







        return v;
    }






}
