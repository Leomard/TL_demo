package com.jzone.tl_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by 28537 on 2017/7/29.
 */

public class ChooseSession extends Activity {

    private Button button1,button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view1);
        button1= (Button) findViewById(R.id.button21);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(ChooseSession.this,CreateSession.class);
                ChooseSession.this.startActivity(intent);

            }
        });
    }
}
