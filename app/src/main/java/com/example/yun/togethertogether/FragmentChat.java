package com.example.yun.togethertogether;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
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
    ArrayList<String> peoplecount;// 스피너 인원수
    String[] titles = {"sky", "destiny", "bebe"};
    Integer[] images = { R.drawable.lovehouse};
    CustomList adapter;
    CustomList adapter2;
    View view;
    int selectedpos=-1;
    Parcelable depoListInstance;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chat, container, false);
        if(savedInstanceState!=null)
        {
            depoListInstance = savedInstanceState.getParcelable("instance");
            list1.onRestoreInstanceState(depoListInstance);
        }


        list1 = (ListView) view.findViewById(R.id.list);
        listItems=new ArrayList<String>();
        peoplecount=new ArrayList<String>();
        //listItems.add("sky");
        //listItems.add("Destiny");
        //listItems.add("fire");
        adapter=new CustomList(getActivity(),listItems);
        list1.setAdapter(adapter);
        fab_open = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_close);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab1 = (FloatingActionButton) view.findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) view.findViewById(R.id.fab2);
        editsearch=(EditText)view.findViewById(R.id.serchedit);
        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);


        list1.setOnItemClickListener(new ListViewItemClickListener());

        list1.setOnItemLongClickListener(new ListViewItemLongClickListener());




        editsearch.addTextChangedListener(new TextWatcher() { //https://www.youtube.com/watch?v=c9yC8XGaSv4 출처

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ArrayList<String> temp=new ArrayList<String>();
                int textlength=editsearch.getText().length(); //검색 입력된 것의 길이
                temp.clear();
                for(int i=0; i<listItems.size(); i++)
                {
                    if(textlength<=listItems.get(i).length()) //검색된 아이템의 길이가 listitems의 길이보다 작거나 같으면
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
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public void onSaveInstanceState(Bundle saved)
    {
        super.onSaveInstanceState(saved);
        saved.putParcelable("instance",list1.onSaveInstanceState());

    }


    protected class ListViewItemClickListener implements AdapterView.OnItemClickListener
    {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {

            Intent intent = new Intent(getActivity(),roomActivity.class);
            ActivityOptions activityOptions=ActivityOptions.makeCustomAnimation(getContext(),R.anim.fromright,R.anim.toleft);
            //intent.putExtra("INPUT_CHATNAME",listItems.get(position).toString());
            //getActivity().setResult(Activity.RESULT_OK,intent);
            // getActivity().finish();

            startActivity(intent,activityOptions.toBundle());
        }
    }




    protected class ListViewItemLongClickListener implements AdapterView.OnItemLongClickListener
    {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
            selectedpos=position;

            AlertDialog.Builder alertDlg = new AlertDialog.Builder(view.getContext());
            // '예' 버튼이 클릭되면
            alertDlg.setMessage("방을 삭제하시겠습니까?");
            alertDlg.setPositiveButton( "Yes", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick( DialogInterface dialog, int which )
                {
                    listItems.remove(selectedpos);
                    // 아래 method를 호출하지 않을 경우, 삭제된 item이 화면에 계속 보여진다.
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();  // AlertDialog를 닫는다.
                }
            });

            // '아니오' 버튼이 클릭되면
            alertDlg.setNegativeButton("No", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick( DialogInterface dialog, int which ) {
                    dialog.dismiss();  // AlertDialog를 닫는다.
                }
            });



            alertDlg.show();
            return true;

        }

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
        Dialog.setContentView(R.layout.chatdialog);
        Dialog2.setContentView(R.layout.chatdialog2);
        final Spinner spinner=(Spinner)Dialog.findViewById(R.id.spin);
        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(getActivity(),R.array.people_count,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter2);
        Button roomcre = (Button) Dialog.findViewById(R.id.roomcreate);
        Button roomcan = (Button) Dialog.findViewById(R.id.roomcancle);
        final EditText roomname = (EditText) Dialog.findViewById(R.id.roomname);

        roomcre.setOnClickListener(this);
        roomcan.setOnClickListener(this);

        int id = v.getId();
        switch (id) {
            case R.id.fab:
                anim();
                break;
            case R.id.fab1:
                anim();
                Intent people =new Intent(getActivity(),f.class);
                startActivity(people);
                break;
            case R.id.fab2:
                anim();
                roomcre.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (v.getId())
                        {
                            case R.id.roomcreate:
                                String roomtext=roomname.getText().toString();
                                peoplecount.add(spinner.getSelectedItem().toString());
                                if(roomtext.length()!=0){
                                    listItems.add(roomtext);
                                    roomname.setText("");
                                    //adapter=new CustomList(getActivity(),listItems);
                                    list1.setAdapter(adapter);
                                    adapter.notifyDataSetChanged(); //변화된 것을 어뎁터에 알려라
                                }
                                break;

                        }
                        Dialog.dismiss();

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
            imageView.setImageResource(images[0]);
            rating.setText( 1+ postion+"째 방");
            if(peoplecount.get(postion).toString().equals("6")) {
                year.setText("3vs3");
            }
            else
                year.setText("4vs4");
            colorpos = postion % 2; //2로나눈 나머지를 colorpos에 저장해서
            if (colorpos == 0) //2로나눈 나머지가 0이면
                rowView.setBackgroundColor(Color.WHITE);
            else //나머지가 0이아니면  핑크색으로 설정
                rowView.setBackgroundColor(Color.rgb(255, 228, 225));


            return rowView;
        }




    }
}

