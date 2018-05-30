package com.example.yun.togethertogether;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.example.yun.togethertogether.R;





public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context context;

    public RecyclerViewAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        switch (position) {
            case 0:
                holder.imageView.setImageResource(R.drawable.love_sentence);
                //  holder.imageView.setBackgroundColor(Color.parseColor("#0f0f00"));
                break;
            case 1:
                holder.imageView.setImageResource(R.drawable.love2);
                //  holder.imageView.setBackgroundColor(Color.parseColor("#0f0f00"));
                break;
            case 2:
                holder.imageView.setImageResource(R.drawable.love3);

                //   holder.imageView.setScaleType();
                //  holder.imageView.setBackgroundColor(Color.parseColor("#00ff00"));
                break;
            case 3:
                holder.imageView.setImageResource(R.drawable.love4);
                // holder.imageView.setBackgroundColor(Color.parseColor("#0000ff"));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.recylcerview_image);
        }
    }

}