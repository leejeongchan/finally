package com.example.yun.togethertogether;

/**
 * Created by JEUNGCHAN on 2018-05-25.
 */

public class chat {

    public String email;

    public String text;

    public chat() {
        // Default constructor required for calls to DataSnapshot.getValue(Comment.class)
    }

    public chat( String text) {

        this.text = text;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
