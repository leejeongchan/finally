package com.example.yun.togethertogether;

/**
 * Created by JEUNGCHAN on 2018-05-25.
 */

public class Friend {

    public String tvEmail;

    public String key;


    public Friend() {
        // Default constructor required for calls to DataSnapshot.getValue(Comment.class)
    }
    public Friend(String email)
    {
        tvEmail=email;
    }

    public String getId() {
        return tvEmail;
    }

    public void setId(String tvEmail) {
        this.tvEmail = tvEmail;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
