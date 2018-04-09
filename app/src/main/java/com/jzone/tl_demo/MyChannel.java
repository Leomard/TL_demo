package com.jzone.tl_demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 28537 on 2017/7/29.
 */

public class MyChannel extends Activity {

    private ListView listView;
    private SimpleAdapter simpleAdapter;
    private List<Map<String,Object>> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_channel_list);
        listView= (ListView) findViewById(R.id.listView121);
        dataList=new ArrayList<Map<String,Object>>();
        simpleAdapter=new SimpleAdapter(this,getData(),R.layout.item,new String[]{"name","ini"},new int[]{R.id.textView91,R.id.textView92});
        listView.setAdapter(simpleAdapter);
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
