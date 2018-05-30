package com.example.yun.togethertogether;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by LEEJC-PC on 2018-05-30.
 */

public class bulletin_board extends AppCompatActivity{
    EditText power3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulletin_board);
        Button power=(Button)findViewById(R.id.btn13);
        Button power2=(Button)findViewById(R.id.btn11);
        power3=(EditText)findViewById(R.id.content1);

        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra("INPUT_TEXT",power3.getText().toString());
                setResult(1,intent);
                finish();

            }
        });
        power2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });


    }

}
