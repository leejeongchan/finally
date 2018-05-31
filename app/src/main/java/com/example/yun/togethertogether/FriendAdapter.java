package com.example.yun.togethertogether;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by JEUNGCHAN on 2018-05-23.
 */

public  class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {

    List<Friend> mFriend;
    String em;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvEmail;
        public Button invbt;
        public ViewHolder(View itemView) {
            super(itemView);

            tvEmail = (TextView)itemView.findViewById(R.id.tvUseremail);
            invbt=(Button)itemView.findViewById(R.id.invitebutton);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public FriendAdapter(List<Friend> mFriend,String email) {
        this.mFriend =  mFriend;
        this.em=email;
    }




    // Create new views (invoked by the layout manager)
    @Override
    public FriendAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        View v=  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_friend, parent, false);


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.tvEmail.setText(mFriend.get(position).getId());
        holder.invbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stFriendId=mFriend.get(position).getKey();
                Intent in = new Intent(v.getContext(),roomActivity.class);
                in.putExtra("destination",stFriendId);



                ActivityOptions activityOptions=ActivityOptions.makeCustomAnimation(v.getContext(),R.anim.fromright,R.anim.toleft);

                v.getContext().startActivity(in,activityOptions.toBundle());
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mFriend.size();
    }
}