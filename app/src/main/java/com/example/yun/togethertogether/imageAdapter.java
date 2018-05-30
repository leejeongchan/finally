package com.example.yun.togethertogether;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * Created by LEEJC-PC on 2018-05-30.
 */

public class imageAdapter extends BaseAdapter{
    public Context mContext;

    public imageAdapter(Context c){
        mContext=c;
    }

    public int getCount(){
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    public Object getItemt(int position){
        return null;
    }

    public long getItemId(int position){
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ImageView imageView;
        if(convertView==null){
            imageView=new ImageView(mContext);
            imageView.setLayoutParams(new Gallery.LayoutParams(485,485));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8,8,8,8);
        }else{
            imageView=(ImageView)convertView;
        }
        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    public Integer[] mThumbIds={
            R.drawable.han1,R.drawable.han2,R.drawable.han3,R.drawable.han4
    };
}
