package com.example.yun.togethertogether;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by LEEJC-PC on 2018-05-30.
 */

public class bulletin_board_in extends AppCompatActivity {
    static final int GET_STRING=1;
    TextView text10,text20,text30;
    Button btn10;
    ArrayList<String> listItems;
    EditText edithong;
    CustomeList2 adapter;
    ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulletin_board_in);

        listItems=new ArrayList<>();
        edithong=(EditText)findViewById(R.id.Editpower);
        list = (ListView)findViewById(R.id.ListViewpower);
        btn10=(Button)findViewById(R.id.btnpower);
        adapter=new CustomeList2(bulletin_board_in.this,listItems);
        list.setAdapter(adapter);


        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItems.add(edithong.getText().toString());
                list.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public class CustomeList2 extends ArrayAdapter<String>
    {

        private final Activity context;
        ArrayList<String> arrString;
        private LayoutInflater inflater;

        public CustomeList2(Activity context,ArrayList<String> arrString)
        {
            super(context,R.layout.listitem123,arrString);
            this.arrString=arrString;
            this.context=context;
        }
        public Context getContext(){
            return super.getContext();
        }


        @Override

        public View getView(final int position, View view, ViewGroup parent){


            final LayoutInflater inflater=context.getLayoutInflater();
            View rowView=inflater.inflate(R.layout.listitem123,null,true);
            text10=(TextView)rowView.findViewById(R.id.this_time);
            text20=(TextView) rowView.findViewById(R.id.Chatting);
            text30=(TextView)rowView.findViewById(R.id.User_ID);

            text20.setText(arrString.get(position));
            text30.setText("익명");
            text10.setText("11:11");

            return rowView;
        }

    }
}
