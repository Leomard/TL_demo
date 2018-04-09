package com.jzone.tl_demo;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by 28537 on 2017/7/31.
 */

public class MyNewTab extends FragmentActivity {
    private FragmentManager fragmentManager;
    private RadioGroup radioGroup;
    private Button button1,button2,button3,button4;
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.my_new_bar);
        MyNewTab.this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title3);
        fragmentManager = getSupportFragmentManager();
        radioGroup = (RadioGroup) findViewById(R.id.rg_tab);
        button1= (Button) findViewById(R.id.b1);
        button2= (Button) findViewById(R.id.b2);
        button3= (Button) findViewById(R.id.b3);
        button4= (Button) findViewById(R.id.b4);
        IniGroup();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment3 fragment = new Fragment3();
        transaction.add(R.id.content,fragment );
        transaction.commit();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
                int s=radioGroup.getCheckedRadioButtonId ();
                if(s==R.id.b1)
                {
                    Fragment fragment=new Fragment3();
                    transaction.replace(R.id.content,fragment );
                    MyNewTab.this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title3);
                    transaction.commit();

                }
                else if(s==R.id.b2)
                {
                    Fragment fragment=new Fragment2();
                    transaction.replace(R.id.content,fragment );
                    MyNewTab.this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title2);
                    transaction.commit();
                }
                else if(s==R.id.b3)
                {
                    Fragment fragment=new Fragment1();
                    transaction.replace(R.id.content,fragment );
                    MyNewTab.this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title1);
                    transaction.commit();
                }
                else if(s==R.id.b4)
                {
                    Fragment fragment=new Fragment4();
                    transaction.replace(R.id.content,fragment );
                    MyNewTab.this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title4);
                    transaction.commit();
                }

            }
        });
    }
    private void IniGroup()
    {
        Drawable drawableWeiHui = getResources().getDrawable(R.drawable.view_1_selector);
        drawableWeiHui.setBounds(0, 0, 70, 70);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        button1.setCompoundDrawables(null, drawableWeiHui, null, null);//只放上面

        Drawable drawableAdd = getResources().getDrawable(R.drawable.view_2_selector);
        drawableAdd.setBounds(0, 0, 70, 70);
        button2.setCompoundDrawables(null, drawableAdd, null, null);

        Drawable drawableRight = getResources().getDrawable(R.drawable.view_3_selector);
        drawableRight.setBounds(0, 0, 70, 70);
       button3.setCompoundDrawables(null, drawableRight, null, null);

        Drawable drawable1 = getResources().getDrawable(R.drawable.view_4_selector);
        drawable1.setBounds(0, 0, 70, 70);
        button4.setCompoundDrawables(null, drawable1, null, null);
        //初始化底部标签
    }
    private void showCustomizeDialog() {
    /* @setView 装入自定义View ==> R.layout.dialog_customize
     * 由于dialog_customize.xml只放置了一个EditView，因此和图8一样
     * dialog_customize.xml可自定义更复杂的View
     */
        AlertDialog.Builder customizeDialog =
                new AlertDialog.Builder(MyNewTab.this);
        final View dialogView = LayoutInflater.from(MyNewTab.this)
                .inflate(R.layout.dialog_enter_password,null);
        customizeDialog.setTitle("请输入密码：");
        customizeDialog.setView(dialogView);
        customizeDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 获取EditView中的输入内容
                        EditText edit_text =
                                (EditText) dialogView.findViewById(R.id.edit_text);
                        Toast.makeText(MyNewTab.this,
                                edit_text.getText().toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
        customizeDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        customizeDialog.show();
    }
}
