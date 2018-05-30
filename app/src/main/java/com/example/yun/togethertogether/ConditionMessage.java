package com.example.yun.togethertogether;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by SS20171206001 on 2018-05-26.
 */

public class ConditionMessage extends AppCompatActivity {
    EditText edit;
    ImageView send;
    ImageView cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conditionmessage);
        send=(ImageView) findViewById(R.id.sendbutton);
        cancel=(ImageView)findViewById(R.id.cancelbutton);
        edit=(EditText)findViewById(R.id.message1) ;


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();

                intent.putExtra("INPUT_TEXT",edit.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
                //  overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_right);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
                //   overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_right);
            }
        });
    }
}