package com.example.yun.togethertogether;

/**
 * Created by JEUNGCHAN on 2018-05-25.
 */

public class chat {

    public String id;

    public String text;

    public chat() {
        // Default constructor required for calls to DataSnapshot.getValue(Comment.class)
    }

    public chat( String text) {

        this.text = text;
    }

    public String getEmail() {
        return id;
    }

    public void setEmail(String email) {
        this.id = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
