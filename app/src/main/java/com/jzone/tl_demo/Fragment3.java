package com.jzone.tl_demo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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

public class Fragment3 extends DialogFragment {

    private EditText edit1;
    private Button button;
    private ListView listView;
    private SimpleAdapter simpleAdapter;
    private List<Map<String,Object>> dataList;
    private View view;
    private List<Channel>channels;
    private AccountApi accountApi;
    private ChannelApi channelApi;
    private SessionApi sessionApi;
    private Contact contact;
    private Bundle bundle;
    private Channel channel;
    private int channelId;
    private int channelType;
    private int anchorId;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view3,container,false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        accountApi= API.getAccountApi();
        channelApi=API.getChannelApi();
        sessionApi=API.getSessionApi();
        edit1= (EditText) getActivity().findViewById(R.id.editText9);
        button= (Button) getActivity().findViewById(R.id.button9);
        listView= (ListView) getActivity().findViewById(R.id.listview9);
        contact=accountApi.whoAmI();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s;
                s=edit1.getText().toString();
                channelApi.searchPublicChannel(accountApi.whoAmI().id,s);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bundle=new Bundle();
                if(!channels.get(position).needPassword)
                {
                    String s=new String();
                    channelApi.focusPublicChannel(contact.id, channels.get(position).cid.getType(), channels.get(position).cid.getId(), API.md5(s));
                    bundle=new Bundle();
                    channelId=channels.get(position).cid.getId();
                    channelType=channels.get(position).cid.getType();
                    anchorId=channels.get(position).owner.i;
                    bundle.putString("Name", channels.get(position).name);
                    bundle.putInt("member", channels.get(position).presenceCount);
                    bundle.putInt("cidId",channels.get(position).cid.getId());
                    bundle.putInt("cidType",channels.get(position).cid.getType());
                    bundle.putString("nickName",channels.get(position).owner.s);
                    bundle.putInt("anchorId",channels.get(position).owner.i);
                    bundle.putBoolean("isHome",channels.get(position).isHome);
                    bundle.putBoolean("ispassword",channels.get(position).needPassword);
                }
                else
                {
                    showInputDialog(channels.get(position).cid.getType(),channels.get(position).cid.getId());
                    bundle=new Bundle();
                    channelId=channels.get(position).cid.getId();
                    channelType=channels.get(position).cid.getType();
                    anchorId=channels.get(position).owner.i;
                    bundle.putString("Name", channels.get(position).name);
                    bundle.putInt("member", channels.get(position).presenceCount);
                    bundle.putInt("cidId",channels.get(position).cid.getId());
                    bundle.putInt("cidType",channels.get(position).cid.getType());
                    bundle.putString("nickName",channels.get(position).owner.s);
                    bundle.putInt("anchorId",channels.get(position).owner.i);
                    bundle.putBoolean("isHome",channels.get(position).isHome);
                    bundle.putBoolean("ispassword",channels.get(position).needPassword);

                }
            }
        });
        channelApi.setOnChannelListener(new OnChannelListener() {
            @Override
            public void onDefaultChannelSet(int i, int i1, int i2) {

            }

            @Override
            public void onAdverChannelsGet(int i, Channel channel, List<Channel> list) {

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
                channels=new ArrayList<Channel>();
                channels=list;
                simpleAdapter=new SimpleAdapter(getActivity(),getData(),R.layout.item,new String[]{"name","ini"},new int[]{R.id.textView91,R.id.textView92});
                listView.setAdapter(simpleAdapter);
                simpleAdapter.notifyDataSetChanged();
                Log.i("Search", "gggggggggggggggggggggggggg ");
            }

            @Override
            public void onPubChannelFocusResult(int i, int i1) {
                if(i>0)
                {
                    Toast.makeText(getActivity(),"关注成功",Toast.LENGTH_SHORT).show();
                    sessionApi.sessionCall(contact.id,channelType,channelId);
                }
                else
                {
                    Toast.makeText(getActivity(),"关注失败",Toast.LENGTH_SHORT).show();
                }
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
        sessionApi.setOnSessionListener(new OnSessionListener() {
            @Override
            public void onSessionEstablished(int i, int i1, int i2) {
                if(i>0&&bundle!=null)
                {
                    Toast.makeText(getActivity(), "会话连接成功", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent();
                    if(contact.id==anchorId)
                    {
                        intent.setClass(getActivity(),MyAnchor.class);
                    }
                    else
                    {
                        intent.setClass(getActivity(),MyAudience.class);
                    }

                    intent.putExtras(bundle);
                    startActivity(intent);
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
                    if(contact.id==anchorId)
                    {
                        intent.setClass(getActivity(),MyAnchor.class);
                    }
                    else
                    {
                        intent.setClass(getActivity(),MyAudience.class);
                    }
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

    public List<Map<String,Object>> getData()
    {

        dataList=new ArrayList<Map<String,Object>>();
        for(int i=0;i<channels.size();i++)
        {
            Map<String,Object>map=new HashMap<String,Object>();
            map.put("name",channels.get(i).name);
            map.put("ini",channels.get(i).presenceCount);
            dataList.add(map);
        }
        return dataList;
    }
    private void showInputDialog(int i1,int i2) {
    /*@setView 装入一个EditView
     */
        final int cidType=i1,cidId=i2;
        final EditText editText = new EditText(getActivity());
        AlertDialog.Builder inputDialog =
                new AlertDialog.Builder(getActivity());
        inputDialog.setTitle("输入密码").setView(editText);
        inputDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        channelApi.focusPublicChannel(contact.id,cidType,cidId,API.md5(editText.getText().toString()));
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = inputDialog.create();
        alertDialog.show();
    }

}

