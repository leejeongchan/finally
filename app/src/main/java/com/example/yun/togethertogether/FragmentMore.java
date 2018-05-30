package com.example.yun.togethertogether;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.support.v4.app.Fragment;

import java.util.ArrayList;

public class FragmentMore extends Fragment {
    TextView text;
    ArrayList<String> arrString;

    static final int GET_STRING=1;
    ImageButton imageView;
    ImageButton imageView1;
    ImageButton imageView2;
    int a=0;
    LinearLayout myLayout;
    ListView list3;
    AnimationDrawable animationDrawable;
    ArrayList<String> listItems;
    CustomeList2 adapter;
    private FloatingActionButton fab;
    int selectedpos=-1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        list3 = (ListView) view.findViewById(R.id.list3);

        listItems=new ArrayList<>();
        listItems.add("미팅 개꿀잼");
        listItems.add("미팅했었는데 애들 잘생겼떠라...");
        listItems.add("제발 제발 제발 ");
        listItems.add("학점보단 과팅!");
        listItems.add("내다리는 시옷 ㅎㅎㅎ");
        adapter=new CustomeList2(getActivity(),listItems);

        list3.setAdapter(adapter);

        fab=(FloatingActionButton)view.findViewById(R.id.fabb);
        fab.isAttachedToWindow();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),bulletin_board.class);
                startActivityForResult(intent,GET_STRING);

            }
        });


        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==GET_STRING)
        {
            if(resultCode==1)
            {
                listItems.add(data.getStringExtra("INPUT_TEXT"));
                list3.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }
    }




    public class CustomeList2 extends ArrayAdapter<String>
    {

        private final Activity context;
        ArrayList<String> arrString;
        private LayoutInflater inflater;

        public CustomeList2(Activity context,ArrayList<String> arrString)
        {
            super(context,R.layout.listitem3,arrString);
            this.arrString=arrString;
            this.context=context;
        }
        public Context getContext(){
            return super.getContext();
        }


        @Override

        public View getView(final int position, View view, ViewGroup parent){


            TextView text1,text2,text3;
            final LayoutInflater inflater=context.getLayoutInflater();
            View rowView=inflater.inflate(R.layout.listitem3,null,true);
            ImageButton imageView=(ImageButton) rowView.findViewById(R.id.imageButton1);
            ImageButton imageView1=(ImageButton)rowView.findViewById(R.id.imageButton2);
            ImageButton imageView2=(ImageButton)rowView.findViewById(R.id.imageButton3);
            final TextView textView4=(TextView)rowView.findViewById(R.id.ID1);
            final TextView textView1=(TextView)rowView.findViewById(R.id.ID2);
            final TextView textView2=(TextView)rowView.findViewById(R.id.ID3);
            TextView textView3=(TextView)rowView.findViewById(R.id.Day1);
            TextView textView=(TextView)rowView.findViewById(R.id.Chat1);


            imageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    a=1;
                    textView1.setText("1"); // 현재있는 값을 받아와야함.

                }
            });



            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popup=new PopupMenu(getContext(),v);
                    popup.getMenuInflater().inflate(R.menu.popup,popup.getMenu());
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch(item.getItemId())
                            {
                                case R.id.report :
                                    Intent power=new Intent(getActivity(),bulletin_board_in.class);
                                    power.putExtra("INPUT_TEXT",arrString.get(position));
                                    startActivity(power);
                                    // 클릭된 해당 뷰의
                                case R.id.like_this :
                                    textView1.setText("1");return true;
                            }
                            return false;

                        }
                    });
                    popup.show();

                }
            });

            textView4.setText("익명");
            textView.setText(arrString.get(position));
            textView3.setText("05 15  11:11");

            return rowView;
        }

    }

}
