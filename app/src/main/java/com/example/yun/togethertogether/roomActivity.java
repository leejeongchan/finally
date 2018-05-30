package com.example.yun.togethertogether;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Comment;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by JEUNGCHAN on 2018-05-20.
 */

public class roomActivity extends AppCompatActivity  {
    static final int GET_STRING=1;

    String str;
    ImageView img;
    TextView txv;
    ImageButton buttonmenu;
    RecyclerView recyclerView;
    ImageView img3;
    EditText ed;
    Button send;
    String email;
    List<chat> mchat;
    private String destionation;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    String[] myDataset={"안녕","오늘","뭐했어","영화볼래?"};
    FirebaseDatabase database;
    @Override
    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);
        setContentView(R.layout.chatroom);

        img=(ImageView)findViewById(R.id.backbutton);
        txv=(TextView)findViewById(R.id.chatRoomFriendName);
        buttonmenu=(ImageButton)findViewById(R.id.menuChatRoomImgView);
        img3=(ImageView)findViewById(R.id.addPhotoImgView);
        ed=(EditText)findViewById(R.id.msgEditText);
        send=(Button)findViewById(R.id.sendMsgBtn);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        destionation=getIntent().getStringExtra("destination");

        database = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url

            email = user.getEmail();


        }
        //상대방 이메일을 받아와서 destination에 넣기

        txv.setText(destionation);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sendText=ed.getText().toString();
                if(sendText.equals("") || sendText.isEmpty())
                {
                    Toast.makeText(roomActivity.this, "내용입력하세요", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(roomActivity.this, sendText, Toast.LENGTH_SHORT).show();
                    Date c = Calendar.getInstance().getTime();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c);


                    DatabaseReference myRef = database.getReference("chat").child(formattedDate);
                    Hashtable<String, String> chat
                            = new Hashtable<String, String>();
                    chat.put("email",email);
                    chat.put("text",sendText);

                    myRef.setValue(chat);


                }
            }
        });
        buttonmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent come=new Intent(roomActivity.this,f.class);
                //Fragment fr=null;
                // fr=new Friendfrag();
                // FragmentManager fm=getSupportFragmentManager();
                // FragmentTransaction fragmentTransaction=fm.beginTransaction();
                // fragmentTransaction.replace(R.id.fragment_container2,fr);
                // fragmentTransaction.addToBackStack(null);
                // fragmentTransaction.commit();
                startActivity(come);
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.chatRoomListView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mchat=new ArrayList<>();
        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(mchat,email);
        mRecyclerView.setAdapter(mAdapter);

        DatabaseReference myRef = database.getReference("chat");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                chat comment = dataSnapshot.getValue(chat.class);

                // [START_EXCLUDE]
                // Update RecyclerView
                mchat.add(comment);
                mAdapter.notifyItemInserted(mchat.size() - 1);

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








    }













}