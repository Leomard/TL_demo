package com.jzone.tl_demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.algebra.sdk.API;
import com.algebra.sdk.AccountApi;
import com.algebra.sdk.DeviceApi;
import com.algebra.sdk.OnAccountListener;
import com.algebra.sdk.entity.Constant;
import com.algebra.sdk.entity.Contact;
import com.algebra.sdk.entity.UserProfile;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

/**
 * Created by 28537 on 2017/7/24.
 */

public class MyMainActivity extends Activity {

    private Handler handler;
    private Button startButton;
    private Button register;
    private EditText edit1, edit2;
    private int uid;
    private Context context;
    private Contact contact;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    private Constant constant;
    private AccountApi accountApi = null;
    private DeviceApi deviceApi = null;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylogin);
        handler = new Handler();
        API.init(MyMainActivity.this);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        }, 500);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                accountApi = API.getAccountApi();
                contact = accountApi.whoAmI();
                if (contact != null) {
                    Intent intent = new Intent();
                    Toast.makeText(MyMainActivity.this, "您已经登录", Toast.LENGTH_SHORT).show();
                    intent.setClass(MyMainActivity.this, MyNewTab.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    MyMainActivity.this.startActivity(intent);
                }
                accountApi.setOnAccountListener(new OnAccountListener() {
                    @Override
                    public void onLogin(int i, int i1, UserProfile userProfile) {
                        if (i > 0) {
                            showToast("登录成功");
                            Intent intent = new Intent();
                            intent.setClass(MyMainActivity.this, MyNewTab.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            MyMainActivity.this.startActivity(intent);
                        } else {
                            showToast("账号或密码错误");
                        }
                    }

                    @Override
                    public void onCreateUser(int i, int i1, String s) {
                        return;
                    }

                    @Override
                    public void onLogout() {
                        return;
                    }

                    @Override
                    public void onSetNickName(int i) {
                        return;
                    }

                    @Override
                    public void onChangePassWord(int i, boolean b) {
                        return;
                    }

                    @Override
                    public void onAskUnbind(int i) {
                        return;
                    }

                    @Override
                    public void onAuthRequestReply(int i, int i1, String s) {
                        return;
                    }

                    @Override
                    public void onAuthBindingReply(int i, int i1, String s) {
                        return;
                    }

                    @Override
                    public void onAuthRequestPassReply(int i, int i1, String s) {
                        return;
                    }

                    @Override
                    public void onAuthResetPassReply(int i, int i1) {
                        return;
                    }

                    @Override
                    public void onFriendsSectionGet(int i, int i1, int i2, int i3, List<Contact> list) {
                        return;
                    }

                    @Override
                    public void onFriendStatusUpdate(int i, int i1, int i2) {
                        return;
                    }

                    @Override
                    public void onShakeScreenAck(int i, int i1, int i2) {
                        return;
                    }

                    @Override
                    public void onShakeScreenReceived(int i, String s, String s1) {
                        return;
                    }

                    @Override
                    public void onSetStatusReturn(int i, boolean b) {
                        return;
                    }

                    @Override
                    public void onHearbeatLost(int i, int i1) {
                        return;
                    }

                    @Override
                    public void onKickedOut(int i, int i1) {
                        return;
                    }

                    @Override
                    public void onSelfStateChange(int i, int i1) {
                        return;
                    }

                    @Override
                    public void onSelfLocationAvailable(int i, Double aDouble, Double aDouble1, Double aDouble2) {
                        return;
                    }

                    @Override
                    public void onSelfLocationReported(int i) {
                        return;
                    }

                    @Override
                    public void onUserLocationNotify(int i, String s, Double aDouble, Double aDouble1) {
                        return;
                    }

                    @Override
                    public void onLogger(int i, String s) {
                        return;
                    }

                    @Override
                    public void onSmsRequestReply(int i) {
                        return;
                    }
                });
            }
        }, 500);
        startButton = (Button) findViewById(R.id.loginin);
        edit1 = (EditText) findViewById(R.id.loginname);
        edit2 = (EditText) findViewById(R.id.loginpassword);
        register = (Button) findViewById(R.id.myregister);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name, password;
                name = edit1.getText().toString();
                password = edit2.getText().toString();
                accountApi.login(name, API.md5(password));
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MyMainActivity.this, MyRegister.class);
                MyMainActivity.this.startActivity(intent);
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void onResume() {

        super.onResume();

    }

    private void showDialog() {
        final AlertDialog.Builder inputDialog =
                new AlertDialog.Builder(this);
        inputDialog.setTitle("申明").setMessage("密码或账号错误");
        inputDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alertDialog = inputDialog.create();
        alertDialog.show();

    }
    public void showToast(String s)
    {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

}
