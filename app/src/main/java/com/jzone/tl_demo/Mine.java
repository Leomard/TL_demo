package com.jzone.tl_demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.algebra.sdk.API;
import com.algebra.sdk.AccountApi;
import com.algebra.sdk.OnAccountListener;
import com.algebra.sdk.entity.Contact;
import com.algebra.sdk.entity.UserProfile;

import java.util.List;

/**
 * Created by 28537 on 2017/7/29.
 */

public class Mine extends Activity {

    private Button button1,button2;
    private EditText editText1,editText2,editText3,editText4,editText5;
    private AccountApi accountApi;
    private Contact contact;
    private int k1,k2,k3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.mine);
        Mine.this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.fortitle);
        accountApi= API.getAccountApi();
        contact=accountApi.whoAmI();
        button1= (Button) findViewById(R.id.button121);
        button2= (Button) findViewById(R.id.button122);
        editText1= (EditText) findViewById(R.id.editText121);
        editText2= (EditText) findViewById(R.id.editText122);
        editText3= (EditText) findViewById(R.id.editText123);
        editText4= (EditText) findViewById(R.id.editText124);
        editText5= (EditText) findViewById(R.id.editText125);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=editText4.getText().toString();
                accountApi.requestBindingPhone(contact.id,contact.name,s);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editText1.getText().toString().trim().isEmpty())
                {
                    String s=editText1.getText().toString();
                    accountApi.setNickName(contact.id,s);
                }
                if(!editText3.getText().toString().trim().isEmpty())
                {
                    String s1,s2;
                    s1=editText2.getText().toString();
                    s2=editText3.getText().toString();
                    accountApi.setPassWord(contact.id,contact.name,API.md5(s1),API.md5(s2));
                }
                if(!editText5.getText().toString().trim().isEmpty())
                {
                    accountApi.commandBindingPhone(contact.id,editText4.getText().toString(),editText5.getText().toString());
                }
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

            }

            @Override
            public void onSetNickName(int i) {
                if(i>0)
                    Toast.makeText(Mine.this,"修改昵称成功",Toast.LENGTH_SHORT).show();
                else
                {
                    Toast.makeText(Mine.this,"修改昵称失败",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onChangePassWord(int i, boolean b) {
                if(b==true)
                    Toast.makeText(Mine.this,"修改密码成功",Toast.LENGTH_SHORT).show();
                else
                {
                    Toast.makeText(Mine.this,"修改密码失败",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onAskUnbind(int i) {

            }

            @Override
            public void onAuthRequestReply(int i, int i1, String s) {
                if(i>0)
                    Toast.makeText(Mine.this,"验证码发送成功",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Mine.this,"验证码发送失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthBindingReply(int i, int i1, String s) {
                if(i>0)
                    Toast.makeText(Mine.this,"手机号绑定成功",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Mine.this,"手机号绑定失败",Toast.LENGTH_SHORT).show();
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
    }
}
