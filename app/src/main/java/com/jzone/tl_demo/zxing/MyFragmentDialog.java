package com.jzone.tl_demo.zxing;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.jzone.tl_demo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 28537 on 2017/8/3.
 */

public class MyFragmentDialog extends DialogFragment {

    private ListView listView;
    private SimpleAdapter simpleAdapter;
    private List<Map<String,Object>> dataList;
    private ArrayList<String> list;
    private int size;
    private Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        list=new ArrayList<String>();
        bundle=getArguments();
        size=bundle.getInt("size");
        list=bundle.getStringArrayList("name");
        dataList=new ArrayList<Map<String,Object>>();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.my_list_dialog, null);
        listView= (ListView) view.findViewById(R.id.lsitmember);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        simpleAdapter=new SimpleAdapter(getActivity(),getData(),R.layout.my_member_item,new String[]{"name"},new int[]{R.id.text_name});
        listView.setAdapter(simpleAdapter);
        builder.setView(view).setPositiveButton("Ok",
                new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int id)
                    {

                    }
                })
                // Add action buttons
                .setNegativeButton("Cancel", null);
        return builder.create();
    }

    private List< Map<String,Object>> getData() {
        for(int i=0;i<size;i++)
        {
            String s1;
            s1=bundle.getStringArrayList("name").get(i);
            Map<String,Object>map=new HashMap<String,Object>();
            map.put("name",s1);
            dataList.add(map);
        }
        return dataList;
    }
}
