package com.example.yun.togethertogether;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by JEUNGCHAN on 2018-05-20.
 */

public class roomActivity extends AppCompatActivity{
    static final int GET_STRING=1;
    String str;
    ImageView img;
    TextView txv;
    ImageView img2;
    RecyclerView recyclerView;
    ImageView img3;
    EditText ed;
    Button send;
    @Override
    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);
        setContentView(R.layout.chatroom);
        img=(ImageView)findViewById(R.id.backbutton);
        txv=(TextView)findViewById(R.id.chatRoomFriendName);
        img2=(ImageView)findViewById(R.id.menuChatRoomImgView);
        recyclerView=(RecyclerView)findViewById(R.id.chatRoomListView);
        img3=(ImageView)findViewById(R.id.addPhotoImgView);
        ed=(EditText)findViewById(R.id.msgEditText);
        send=(Button)findViewById(R.id.sendMsgBtn);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }





}
