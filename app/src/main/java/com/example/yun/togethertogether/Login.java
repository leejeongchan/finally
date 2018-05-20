package com.example.yun.togethertogether;

import android.graphics.Color;
import android.os.Bundle;

import android.content.Intent;
import android.database.Cursor;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
//test

public class Login extends Login_db {
    EditText idText;
    EditText passwordText;
    String id;
    String password;
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        layout = findViewById(R.id.loginLayout);
        layout.setBackgroundColor(Color.WHITE);

        idText = (EditText)findViewById(R.id.email);
        passwordText = (EditText)findViewById(R.id.password);
    }

    public void login(View v) {
//        if (database != null) {
//            Cursor cursor = database.rawQuery("SELECT name, email, password FROM" + tableName, null);
//            int count = cursor.getCount();
//            for (int i = 0; i < count; i++) {
//                cursor.moveToNext();
//                Lname = cursor.getString(0);
//                Lemail = cursor.getString(1);
//                Lpassword = cursor.getString(2);
//            }
//            id = idText.getText().toString();
//            password = passwordText.getText().toString();
//
//            if (id.equals(Lemail) && password.equals(Lpassword)) {
                Intent main = new Intent(this, MainActivity.class);
              //  main.putExtra("splash", "splash");
                startActivity(main);

//                Toast.makeText(getApplicationContext(), Lname + "님 환영합니다!", Toast.LENGTH_SHORT).show();
//            } else
//                Toast.makeText(getApplicationContext(), "아이디/비밀번호가 틀렸거나 없습니다.", Toast.LENGTH_SHORT).show();
//
//            cursor.close();
//        }

    }

    public void member(View view){
        Intent reg_member = new Intent(getApplication(),member_rgeister.class);
        reg_member.putExtra("splash","splash");
        startActivity(reg_member);
    }
}