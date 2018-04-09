package com.jzone.tl_demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.algebra.sdk.API;
import com.algebra.sdk.AccountApi;
import com.algebra.sdk.DeviceApi;
import com.algebra.sdk.OnAccountListener;
import com.algebra.sdk.entity.Constant;
import com.algebra.sdk.entity.Contact;
import com.algebra.sdk.entity.UserProfile;

import java.util.List;

/**
 * Created by 28537 on 2017/7/24.
 */

public class MyRegister extends Activity {

    private AccountApi accountApi = null;
    private DeviceApi deviceApi = null;
    private Button Button1, Button2;
    private EditText editText;
    private Constant constant;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myregister);

        Button1 = (Button) findViewById(R.id.button11);
        Button2 = (Button) findViewById(R.id.button12);

        editText = (EditText) findViewById(R.id.editText11);
        accountApi = API.getAccountApi();
        deviceApi = API.getDeviceApi();

        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ss;
                ss = editText.getText().toString();
                accountApi.createVisitor(deviceApi.toString(), ss);
            }
        });
        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MyRegister.this, MyMainActivity.class);
                MyRegister.this.startActivity(intent);
            }
        });
        accountApi.setOnAccountListener(new OnAccountListener() {
            @Override
            public void onLogin(int i, int i1, UserProfile userProfile) {

            }

            @Override
            public void onCreateUser(int i, int i1, String s) {
                if (i1 == constant.ACCOUNT_RESULT_OK || i1 == constant.ACCOUNT_RESULT_ALREADY_LOGIN) {
                    Intent intent = new Intent();
                    intent.setClass(MyRegister.this,MyNewTab.class);
                    MyRegister.this.startActivity(intent);
                } else {
                    showDialog("输入的用户名或者密码错误");
                }
            }

            @Override
            public void onLogout() {

            }

            @Override
            public void onSetNickName(int i) {

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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }

    ;

    private void showDialog(String c) {
        new  AlertDialog.Builder(MyRegister.this)
                .setTitle("信息" )
                .setMessage(c )
                .setPositiveButton("确定" ,  null )
                .create()
                .show();

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

}
