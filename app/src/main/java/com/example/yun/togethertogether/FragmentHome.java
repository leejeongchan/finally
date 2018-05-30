package com.example.yun.togethertogether;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.UI_MODE_SERVICE;

public class FragmentHome  extends Fragment{


    Button profileview;
    static final int GET_STRING=1;
    TextView text;
    ImageView profileimage;
    public static final int PICK_FROM_ALBUM = 3;
    public static final int PICK_FROM_CAMERA=4;
    public static final int CROP_FROM_iMAGE=2;
    int time=0;
    ImageView lovebar1;
    ImageView lovebar2;
    ImageView lovebar3;
    ImageView lovebar4;
    ImageView mainlove;
    Uri imageUri;
    Uri albumURI;
    Drawable drawable;
    GridView gridView;
    Gallery gallery;
    RecyclerView recyclerView;
    PagerSnapHelper pagerSnapHelper;
    LinearLayoutManager layoutManager;
    Handler handler;
    Runnable runnable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home, container, false);
        profileview = (Button) view.findViewById(R.id.profile_info);
        text = (TextView) view.findViewById(R.id.textline);
        profileimage = (ImageView) view.findViewById(R.id.imageView);
//        lovebar1 = (ImageView) view.findViewById(R.id.lovebar01);
//        lovebar2 = (ImageView) view.findViewById(R.id.lovebar02);
//        lovebar3 = (ImageView) view.findViewById(R.id.lovebar03);
//        lovebar4 = (ImageView) view.findViewById(R.id.lovebar04);
        //mainlove = (ImageView) view.findViewById(R.id.love);
        gridView = (GridView) view.findViewById(R.id.grid);
        recyclerView = (RecyclerView) view.findViewById(R.id.slide_recyclerview);

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        pagerSnapHelper = new PagerSnapHelper();
        if (handler == null) {
            handler = new Handler();
            runnable = new Runnable() {
                @Override
                public void run() {
                    if (layoutManager.findFirstVisibleItemPosition() != 2) {
                        recyclerView.smoothScrollToPosition(layoutManager.findFirstVisibleItemPosition() + 1);
                    } else {
                        recyclerView.smoothScrollToPosition(0);
                    }
                    handler.postDelayed(this, 5000);
                }
            };

            handler.postDelayed(runnable, 5000);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(new RecyclerViewAdapter(getContext()));
            pagerSnapHelper.attachToRecyclerView(recyclerView);

        }

        if (ContextCompat.checkSelfPermission(getActivity(),
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    android.Manifest.permission.READ_EXTERNAL_STORAGE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

        gridView.setAdapter(new imageAdapter(getActivity()));

        profileview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getActivity(),"dfdf",Toast.LENGTH_SHORT).show();

                final CharSequence[] items = {"상태메세지", "사진촬영", "앨범에서 사진 선택","기본 이미지로 변경"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("프로필 설정");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0:
                                Toast.makeText(getActivity(),"상태메세지 변경하는거",Toast.LENGTH_SHORT).show();
                                Intent in=new Intent(getActivity(),ConditionMessage.class);
                                startActivityForResult(in,GET_STRING);
                                //  getActivity().overridePendingTransition(R.anim.fade,R.anim.anim_slide_out_right);
                                break;
                            case 1:
//                                Intent takecamera=new Intent(getActivity(),tabActivity1.class);
//                                startActivityForResult(takecamera,PICK_FROM_CAMERA);
                                Toast.makeText(getActivity(),"사진 촬영해서 프로필 사진 변경",Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                Toast.makeText(getActivity(),"앨범에서 사진가져오기",Toast.LENGTH_SHORT).show();
                                doTakeAlbumAction();
                                break;
                            case 3:
                                profileimage.setImageDrawable(getResources().getDrawable(R.drawable.normal_imgae));
                            default:
                                break;
                        }

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }

        });

        return view;
    };


    public void onActivityResult(int requestCode,int resultCode,Intent data) {

        switch (requestCode)
        {
            case GET_STRING:
                if(resultCode==RESULT_OK){
                    text.setText(data.getStringExtra("INPUT_TEXT"));
                }
                break;
            case PICK_FROM_ALBUM:
                Uri image=data.getData();
                try {
                    Bitmap bitmap=MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),image);
                    profileimage.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case PICK_FROM_CAMERA:
                Toast.makeText(getActivity(),"efdf",Toast.LENGTH_SHORT).show();
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


    public void doTakeAlbumAction(){
        Intent camera=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(camera,PICK_FROM_ALBUM);
    }







}