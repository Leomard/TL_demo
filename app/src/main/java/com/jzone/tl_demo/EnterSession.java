package com.jzone.tl_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by 28537 on 2017/7/29.
 */

public class EnterSession extends Activity {

    private EditText edit1,edit2;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_session);
        edit1= (EditText) findViewById(R.id.editText71);
        edit2= (EditText) findViewById(R.id.editText72);
        button= (Button) findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1,s2;
                s1=edit1.getText().toString();
                s2=edit2.getText().toString();
                Intent intent=new Intent();
                intent.setClass(EnterSession.this,MyMain.class);
                EnterSession.this.startActivity(intent);
            }
        });
    }
}
