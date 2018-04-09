package com.jzone.tl_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 28537 on 2017/7/29.
 */

public class FindChannel extends Activity {

    private EditText edit;
    private Button button;
    private ListView listView;
    private SimpleAdapter simpleAdapter;
    private List<Map<String,Object>>dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view3);
        edit= (EditText) findViewById(R.id.editText9);
        button= (Button) findViewById(R.id.button9);
        listView= (ListView) findViewById(R.id.listview9);
        dataList=new ArrayList<Map<String,Object>>();
        simpleAdapter=new SimpleAdapter(this,getData(),R.layout.item,new String[]{"name","ini"},new int[]{R.id.textView91,R.id.textView92});
        listView.setAdapter(simpleAdapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(FindChannel.this,ChannelInformation.class);
                FindChannel.this.startActivity(intent);
            }
        });
    }
    public List<Map<String,Object>> getData()
    {
        for(int i=0;i<20;i++)
        {
            Map<String,Object>map=new HashMap<String,Object>();
            map.put("name","无名");
            map.put("ini","无名");
            dataList.add(map);
        }
        return dataList;
    }
}
