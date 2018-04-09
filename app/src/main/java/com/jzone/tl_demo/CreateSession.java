package com.jzone.tl_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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
import java.util.List;

/**
 * Created by 28537 on 2017/7/29.
 */

public class CreateSession extends Activity {

    private Bundle bundle;
    private EditText edit1,edit2;
    private Button button;
    private CheckBox checkBox;
    private AccountApi accountApi;
    private ChannelApi channelApi;
    private SessionApi sessionApi;
    private Contact contact;
    private String channelName;
    private int channelId;
    private List<Channel>channels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle=new Bundle();
        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.create_session);
        CreateSession.this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.fortitle);
        edit1= (EditText) findViewById(R.id.editText61);
        edit2= (EditText) findViewById(R.id.editText62);
        button= (Button) findViewById(R.id.button6);
        checkBox= (CheckBox) findViewById(R.id.checkBox6);
        accountApi= API.getAccountApi();
        channelApi=API.getChannelApi();
        sessionApi=API.getSessionApi();
        contact=accountApi.whoAmI();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1,s2;
                channelName=edit1.getText().toString();
                s2=edit2.getText().toString();
                if(checkBox.isChecked())
                {
                    channelApi.createPublicChannel(contact.id,channelName,API.md5(s2));
                }
                else
                {
                    s2=null;
                    channelApi.createPublicChannel(contact.id,channelName,s2);
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
                channels=new ArrayList<Channel>();
                channels=list;
                bundle=new Bundle();
                Channel s;
                for(int k=0;k<list.size();k++)
                {
                    if(list.get(k).cid.getId()==channelId)
                    {
                        bundle.putString("Name", list.get(k).name);
                        bundle.putInt("member", list.get(k).presenceCount);
                        bundle.putInt("cidId",list.get(k).cid.getId());
                        bundle.putInt("cidType",list.get(k).cid.getType());
                        bundle.putString("nickName",list.get(k).owner.s);
                        bundle.putInt("anchorId",list.get(k).owner.i);
                        bundle.putBoolean("isHome",list.get(k).isHome);
                        bundle.putBoolean("ispassword",channels.get(k).needPassword);
                        sessionApi.sessionCall(contact.id,list.get(k).cid.getType(),list.get(k).cid.getId());
                        break;
                    }
                }
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
                if(i>0)
                {
                    Toast.makeText(CreateSession.this,"频道创建成功",Toast.LENGTH_SHORT).show();
                    channelId=i2;
                    channelApi.channelListGet(contact.id);
                }
                else
                {
                    Toast.makeText(CreateSession.this,"频道创建失败",Toast.LENGTH_SHORT).show();
                }
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
        sessionApi.setOnSessionListener(new OnSessionListener() {
            @Override
            public void onSessionEstablished(int i, int i1, int i2) {
                if(i>0&&bundle!=null)
                {
                    Toast.makeText(CreateSession.this, "会话连接成功", Toast.LENGTH_SHORT).show();
                    Toast.makeText(CreateSession.this, "会话进入成功", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent();
                    intent.setClass(CreateSession.this,MyAnchor.class);
                    intent.putExtras(bundle);
                    CreateSession.this.startActivity(intent);
                    Log.i("Link", "gggggggggggggggggggg ");
                }
                else if(i<0)
                    Toast.makeText(CreateSession.this,"会话连接失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSessionReleased(int i, int i1, int i2) {

            }

            @Override
            public void onSessionGet(int i, int i1, int i2, int i3) {
                if(i>0&&bundle!=null)
                {
                    Toast.makeText(CreateSession.this, "会话进入成功", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent();
                    intent.setClass(CreateSession.this,MyAnchor.class);
                    bundle.putInt("Ini",i3);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else if(i<0)
                    Toast.makeText(CreateSession.this,"会话进入失败",Toast.LENGTH_SHORT).show();
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
}
