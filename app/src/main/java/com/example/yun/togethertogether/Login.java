package com.example.yun.togethertogether;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
//test

public class Login extends AppCompatActivity {
    EditText idText;
    EditText passwordText;
    String id;
    String password;
    LinearLayout layout;
    Button rgbutton;
    Button loginbt;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    String TAG="Ma";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        layout = findViewById(R.id.loginLayout);
        layout.setBackgroundColor(Color.WHITE);
        rgbutton=(Button)findViewById(R.id.reg_Button);
        idText = (EditText)findViewById(R.id.email);
        passwordText = (EditText)findViewById(R.id.password);
        loginbt=(Button)findViewById(R.id.Login_Button);
        mAuth=FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user!=null)
                {
                    Log.d(TAG,"sadfsadf"+user.getUid());

                }
                else
                {
                    Log.d(TAG,"out");
                }
            }
        };



        rgbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id=idText.getText().toString();
                password=passwordText.getText().toString();
                //Toast.makeText(Login.this, id+password, Toast.LENGTH_SHORT).show();
                registerUser(id,password);
                String UId=mAuth.getCurrentUser().getUid();
                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = df.format(c);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Users").child(formattedDate);
                Hashtable<String, String> Friend
                        = new Hashtable<String, String>();
                Friend.put("id",id);
                myRef.setValue(Friend);

            }
        });
        loginbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id=idText.getText().toString();
                password=passwordText.getText().toString();
                //Toast.makeText(Login.this, id+password, Toast.LENGTH_SHORT).show();
                userLogin(id,password);

            }
        });

    }
    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop(){
        super.onStop();
        if(mAuthListener!=null)
        {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void registerUser(String email,String password1)
    {
        mAuth.createUserWithEmailAndPassword(email, password1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:success"+task.isSuccessful());


                        Toast.makeText(Login.this, "Auth Success1", Toast.LENGTH_SHORT).show();

                        if (!task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Login.this, "Auth failed2", Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
        });
    }

    public void userLogin(String email,String password2){
        mAuth.signInWithEmailAndPassword(email, password2)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "Sign:success"+task.isSuccessful());
                        Intent main = new Intent(Login.this, MainActivity.class);

                        startActivity(main);

                        Toast.makeText(Login.this, "Auth Success3", Toast.LENGTH_SHORT).show();

                        if (!task.isSuccessful()) {
                            Log.v(TAG,"signwithemail",task.getException());
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Login.this, "Auth failed4", Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }



 //   public void login(View v) {
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
//                Intent main = new Intent(this, MainActivity.class);
              //  main.putExtra("splash", "splash");
//                startActivity(main);

//                Toast.makeText(getApplicationContext(), Lname + "님 환영합니다!", Toast.LENGTH_SHORT).show();
//            } else
//                Toast.makeText(getApplicationContext(), "아이디/비밀번호가 틀렸거나 없습니다.", Toast.LENGTH_SHORT).show();
//
//            cursor.close();
//        }

 //   }

//    public void member(View view){
//        Intent reg_member = new Intent(getApplication(),member_rgeister.class);
//        reg_member.putExtra("splash","splash");
//        startActivity(reg_member);
//    }
}