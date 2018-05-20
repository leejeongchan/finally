package com.example.yun.togethertogether;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class FragmentChat extends Fragment implements View.OnClickListener {
    LinearLayout myLayout;
    AnimationDrawable animationDrawable;
    ListView list1,list2;

    EditText editsearch;
    private FloatingActionButton fab;
    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
    private Animation fab_open, fab_close;
    private Boolean isFabOpen = false;
    public String str;
    ArrayList<String> listItems;
    String[] titles = {"sky", "destiny", "bebe"};
    Integer[] images = {R.drawable.house, R.drawable.lovehouse, R.drawable.love_sentence};
    CustomList adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        list1 = (ListView) view.findViewById(R.id.list);
        listItems=new ArrayList<>();
        listItems.add("sky");
        listItems.add("Destiny");
        listItems.add("fire");
        list1.setAdapter(new CustomList(getActivity(),listItems));
        fab_open = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_close);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab1 = (FloatingActionButton) view.findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) view.findViewById(R.id.fab2);
        editsearch=(EditText)view.findViewById(R.id.serchedit);
        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);

        editsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ArrayList<String> temp=new ArrayList<String>();
                int textlength=editsearch.getText().length();
                temp.clear();
                for(int i=0; i<listItems.size(); i++)
                {
                    if(textlength<=listItems.get(i).length())
                    {
                        if(editsearch.getText().toString().equalsIgnoreCase((String)listItems.get(i).subSequence(0,textlength)))
                        {
                            temp.add(listItems.get(i));
                        }
                    }
                }
                list1.setAdapter(new CustomList(getActivity(),temp));

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        return view;
    }


    public void anim() {

        if (isFabOpen) {
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;
        } else {
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;
        }
    }

    @Override
    public void onClick(View v) {
        final Dialog Dialog = new Dialog(getActivity());
        final Dialog Dialog2=new Dialog(getActivity());
        Dialog.setTitle("로그인 화면");
        Dialog.setContentView(R.layout.chatdialog);
        Dialog2.setTitle("검색 화면");
        Dialog2.setContentView(R.layout.chatdialog2);
        Spinner spinner=(Spinner)Dialog.findViewById(R.id.spin);
        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(getActivity(),R.array.people_count,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter2);
        Button roomcre = (Button) Dialog.findViewById(R.id.roomcreate);
        Button roomcan = (Button) Dialog.findViewById(R.id.roomcancle);
        final EditText roomname = (EditText) Dialog.findViewById(R.id.roomname);
        //final EditText edit1=(EditText)Dialog2.findViewById(R.id.searchname);
        //Button searchconfirm=(Button)Dialog2.findViewById(R.id.confirm);
        //Button searchcancle=(Button)Dialog2.findViewById(R.id.serchcancle);

        int id = v.getId();
        switch (id) {
            case R.id.fab:
                anim();
                break;
            case R.id.fab1:
                anim();
                break;
            case R.id.fab2:
                anim();
                roomcre.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (roomname.getText().toString().trim().length() > 0) {
                            Toast.makeText(getActivity(),
                                    "로그인 성공", Toast.LENGTH_LONG).show();
                            str=roomname.getText().toString();

                            Dialog.dismiss();
                        } else {
                            Toast.makeText(getActivity(),
                                    "다시 입력하시오", Toast.LENGTH_LONG).show();

                        }

                    }
                });
                roomcan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog.dismiss();
                    }
                });
                Dialog.show();
                break;
        }
    }

    public class CustomList extends ArrayAdapter<String> {
        private final Context context;
        ArrayList<String> arrString;
        private LayoutInflater inflater;

        public CustomList(Activity context, ArrayList<String> arrString) {
            super(context, R.layout.listitem, arrString);
            this.context = context;
            this.arrString=arrString;
            this.inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }
        public Context getContext(){
            return super.getContext();
        }

        @Override
        public View getView(int postion, View view, ViewGroup parent) {
            int colorpos;

            View rowView = inflater.inflate(R.layout.listitem, null, true);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.image);
            imageView.setBackground(new ShapeDrawable(new OvalShape()));
            imageView.setClipToOutline(true);

            TextView title = (TextView) rowView.findViewById(R.id.title);
            TextView rating = (TextView) rowView.findViewById(R.id.rating);

            TextView year = (TextView) rowView.findViewById(R.id.releaseYear);

            title.setText(arrString.get(postion));
            imageView.setImageResource(images[postion]);
            rating.setText("9.0" + postion);
            year.setText("4:00PM");

            colorpos = postion % 2; //2로나눈 나머지를 colorpos에 저장해서
            if (colorpos == 0) //2로나눈 나머지가 0이면
                rowView.setBackgroundColor(Color.WHITE);
            else //나머지가 0이아니면  핑크색으로 설정
                rowView.setBackgroundColor(Color.rgb(255, 228, 225));



            return rowView;
        }




    }
}


