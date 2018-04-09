package com.jzone.tl_demo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.algebra.sdk.API;
import com.algebra.sdk.AccountApi;
import com.algebra.sdk.ChannelApi;
import com.algebra.sdk.OnAccountListener;
import com.algebra.sdk.OnChannelListener;
import com.algebra.sdk.entity.Channel;
import com.algebra.sdk.entity.Contact;
import com.algebra.sdk.entity.UserProfile;
import com.jzone.tl_demo.zxing.activity.CaptureActivity;

import java.util.List;
import java.util.logging.Handler;

/**
 * Created by 28537 on 2017/7/29.
 */

public class Fragment4 extends Fragment {
    private TextView textView1,textView2;
    private Button button_self,button_exit,button_edit,button_foucs;
    private ImageView button_zxing;
    private AccountApi accountApi;
    private ChannelApi channelApi;
    private Contact contact;
    private Handler handler;
    private int cidType,cidId;
    private Boolean needPassword;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view4,container,false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        button_zxing= (ImageView) getActivity().findViewById(R.id.erweima);
        textView1= (TextView) getActivity().findViewById(R.id.textView51);
        textView2= (TextView) getActivity().findViewById(R.id.textView52);
        button_self= (Button) getActivity().findViewById(R.id.button51);
        button_exit= (Button) getActivity().findViewById(R.id.button_exit);
        button_foucs= (Button) getActivity().findViewById(R.id.button_foucs);
        button_edit= (Button) getActivity().findViewById(R.id.button_edit);
        channelApi=API.getChannelApi();
        accountApi= API.getAccountApi();
        contact=accountApi.whoAmI();
        textView1.setText(contact.name);
        button_zxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openCameraIntent = new Intent(getActivity(),CaptureActivity.class);
                getActivity().startActivityForResult(openCameraIntent, 0);
            }
        });
        button_foucs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity(),MyFoucs.class);
                getActivity().startActivity(intent);
            }
        });
        button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity(),MyEdit.class);
                getActivity().startActivity(intent);
            }
        });
        if(contact.phone!=null)
        {
            textView2.setText(String.valueOf(contact.phone));
        }
        button_self.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity(),Mine.class);
                getActivity().startActivity(intent);
            }
        });
        button_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountApi.logout(contact.id);
                API.leave();
                android.os.Handler handler=new android.os.Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), MyMainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        getActivity().startActivity(intent);
                    }
                },1000);
            }
        });
        accountApi.setOnAccountListener(new OnAccountListener() {
            @Override
            public void onLogin(int i, int i1, UserProfile userProfile) {

            }

            @Override
            public void onCreateUser(int i, int i1, String s) {

            }

            @Override
            public void onLogout() {
                Toast.makeText(getActivity(),"登出成功",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onSetNickName(int i) {
                contact=accountApi.whoAmI();
                textView1.setText(contact.name);
            }

            @Override
            public void onChangePassWord(int i, boolean b) {

            }

            @Override
            public void onAskUnbind(int i) {

            }

            @Override
            public void onAuthRequestReply(int i, int i1, String s) {

            }

            @Override
            public void onAuthBindingReply(int i, int i1, String s) {
                textView2.setText(contact.phone);
            }

            @Override
            public void onAuthRequestPassReply(int i, int i1, String s) {

            }

            @Override
            public void onAuthResetPassReply(int i, int i1) {

            }

            @Override
            public void onFriendsSectionGet(int i, int i1, int i2, int i3, List<Contact> list) {

            }

            @Override
            public void onFriendStatusUpdate(int i, int i1, int i2) {

            }

            @Override
            public void onShakeScreenAck(int i, int i1, int i2) {

            }

            @Override
            public void onShakeScreenReceived(int i, String s, String s1) {

            }

            @Override
            public void onSetStatusReturn(int i, boolean b) {

            }

            @Override
            public void onHearbeatLost(int i, int i1) {

            }

            @Override
            public void onKickedOut(int i, int i1) {

            }

            @Override
            public void onSelfStateChange(int i, int i1) {

            }

            @Override
            public void onSelfLocationAvailable(int i, Double aDouble, Double aDouble1, Double aDouble2) {

            }

            @Override
            public void onSelfLocationReported(int i) {

            }

            @Override
            public void onUserLocationNotify(int i, String s, Double aDouble, Double aDouble1) {

            }

            @Override
            public void onLogger(int i, String s) {

            }

            @Override
            public void onSmsRequestReply(int i) {

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

            }

            @Override
            public void onPubChannelFocusResult(int i, int i1) {
                if(i>0)
                {
                    Toast.makeText(getActivity(),"关注成功，从我的关注进入",Toast.LENGTH_SHORT).show();
                    Log.i("success", "onPubChannelFocusResult: success");
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

    }

    @Override
    public void onResume() {
        super.onResume();
        contact=accountApi.whoAmI();
        textView1.setText(contact.name);
        textView2.setText(contact.phone);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle s=data.getExtras();
        String ss=s.getString("result");
        int i=ss.indexOf("#");
        cidType=Integer.valueOf(ss.substring(0,i));
        int i2=ss.indexOf("#",i+1);
        cidId=Integer.valueOf(ss.substring(i+1,i2));
        needPassword=Boolean.valueOf(ss.substring(i2+1,ss.length()));
        if(needPassword==true)
        {
            showInputDialog(cidType,cidId);
        }
        else
        {
            channelApi.focusPublicChannel(contact.id,cidType,cidId,null);
        }
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

