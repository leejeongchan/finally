package com.example.yun.togethertogether;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    String[] myDataset={"안녕","오늘","뭐했어","영화볼래?"};
    @Override
    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);
        setContentView(R.layout.chatroom);
        img=(ImageView)findViewById(R.id.backbutton);
        txv=(TextView)findViewById(R.id.chatRoomFriendName);
        img2=(ImageView)findViewById(R.id.menuChatRoomImgView);
        img3=(ImageView)findViewById(R.id.addPhotoImgView);
        ed=(EditText)findViewById(R.id.msgEditText);
        send=(Button)findViewById(R.id.sendMsgBtn);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.chatRoomListView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }






}
