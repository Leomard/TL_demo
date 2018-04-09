package com.jzone.tl_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.algebra.sdk.API;
import com.algebra.sdk.AccountApi;
import com.algebra.sdk.ChannelApi;
import com.algebra.sdk.OnChannelListener;
import com.algebra.sdk.OnSessionListener;
import com.algebra.sdk.SessionApi;
import com.algebra.sdk.entity.Channel;
import com.algebra.sdk.entity.Contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 28537 on 2017/7/29.
 */

public class Fragment2 extends Fragment {

    private ListView listView;
    private SimpleAdapter simpleAdapter;
    private List<Map<String,Object>>dataList;
    private View view;
    private AccountApi accountApi;
    private ChannelApi channelApi;
    private SessionApi sessionApi;
    private List<Channel>channels;
    private Contact contact;
    private Bundle bundle;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view2,container,false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView= (ListView) getActivity().findViewById(R.id.listView31);
        accountApi= API.getAccountApi();
        channelApi=API.getChannelApi();
        sessionApi=API.getSessionApi();
        contact=accountApi.whoAmI();
        channels=new ArrayList<Channel>();
        dataList=new ArrayList<Map<String,Object>>();
        channelApi.setOnChannelListener(new OnChannelListener() {
            @Override
            public void onDefaultChannelSet(int i, int i1, int i2) {

            }

            @Override
            public void onAdverChannelsGet(int i, Channel channel, List<Channel> list) {
                channels=list;
                Log.d("get", "adver ");
                simpleAdapter=null;
                simpleAdapter=new SimpleAdapter(getActivity(),getData(),R.layout.item,new String[]{"name","ini"},new int[]{R.id.textView91,R.id.textView92});
                listView.setAdapter(simpleAdapter);
            }

            @Override
            public void onChannelListGet(int i, Channel channel, List<Channel> list) {

            }

            @Override
            public void onChannelMemberListGet(int i, int i1, int i2, List<Contact> list) {

            }

            @Override
            public void onChannelNameChanged(int i, int i1, int i2, String s) {

            }

            @Override
            public void onChannelAdded(int i, int i1, int i2, String s) {

            }

            @Override
            public void onChannelRemoved(int i, int i1, int i2) {

            }

            @Override
            public void onChannelMemberAdded(int i, int i1, List<Contact> list) {

            }

            @Override
            public void onChannelMemberRemoved(int i, int i1, List<Integer> list) {

            }

            @Override
            public void onPubChannelCreate(int i, int i1, int i2) {

            }

            @Override
            public void onPubChannelSearchResult(int i, List<Channel> list) {

            }

            @Override
            public void onPubChannelFocusResult(int i, int i1) {

            }

            @Override
            public void onPubChannelUnfocusResult(int i, int i1) {

            }

            @Override
            public void onPubChannelRenamed(int i, int i1) {

            }

            @Override
            public void onPubChannelDeleted(int i, int i1) {

            }

            @Override
            public void onCallMeetingStarted(int i, int i1, int i2, List<Contact> list) {

            }

            @Override
            public void onCallMeetingStopped(int i, int i1) {

            }
        });
        if(contact!=null)
            channelApi.adverChannelsGet(contact.id);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                bundle=new Bundle();

                bundle.putString("Name", channels.get(position).name);
                bundle.putInt("member", channels.get(position).presenceCount);
                bundle.putInt("cidId",channels.get(position).cid.getId());
                bundle.putInt("cidType",channels.get(position).cid.getType());
                bundle.putString("nickName",channels.get(position).owner.s);
                bundle.putInt("anchorId",channels.get(position).owner.i);
                bundle.putBoolean("isHome",channels.get(position).isHome);
                bundle.putBoolean("ispassword",channels.get(position).needPassword);
                //intent.putExtras(bundle);
                sessionApi.sessionCall(contact.id,channels.get(position).cid.getType(),channels.get(position).cid.getId());
               // startActivity(intent);
            }
        });
        sessionApi.setOnSessionListener(new OnSessionListener() {
            @Override
            public void onSessionEstablished(int i, int i1, int i2) {
                if(i>0&&bundle!=null)
                {
                    Toast.makeText(getActivity(), "会话连接成功", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(), "会话进入成功", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent();
                    intent.setClass(getActivity(),MyMain.class);
                    intent.putExtras(bundle);
                    getActivity().startActivity(intent);
                    Log.i("Link", "gggggggggggggggggggg ");
                }
                else if(i<0)
                    Toast.makeText(getActivity(),"会话连接失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSessionReleased(int i, int i1, int i2) {

            }

            @Override
            public void onSessionGet(int i, int i1, int i2, int i3) {
                if(i>0&&bundle!=null)
                {
                    Toast.makeText(getActivity(), "会话进入成功", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent();
                    intent.setClass(getActivity(),MyMain.class);
                    bundle.putInt("Ini",i3);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else if(i<0)
                    Toast.makeText(getActivity(),"会话进入失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSessionPresenceAdded(int i, int i1, List<Contact> list) {

            }

            @Override
            public void onSessionPresenceRemoved(int i, int i1, List<Integer> list) {

            }

            @Override
            public void onDialogEstablished(int i, int i1, int i2, List<Integer> list) {

            }

            @Override
            public void onDialogLeaved(int i, int i1) {

            }

            @Override
            public void onDialogPresenceAdded(int i, int i1, List<Integer> list) {

            }

            @Override
            public void onDialogPresenceRemoved(int i, int i1, List<Integer> list) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public List<Map<String,Object>> getData()
    {
        Log.i("List",String.valueOf(channels.size()) );
        for(int i=0;i<channels.size();i++)
        {

            String s1;
            s1=channels.get(i).name;
            Map<String,Object>map=new HashMap<String,Object>();
            map.put("name",s1);
            map.put("ini",channels.get(i).presenceCount);
            dataList.add(map);
        }
        return dataList;
    }
}

