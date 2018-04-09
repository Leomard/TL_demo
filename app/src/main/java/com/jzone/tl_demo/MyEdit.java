package com.jzone.tl_demo;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 28537 on 2017/8/1.
 */

public class MyEdit extends Activity {

    private EditText edit;
    private Button button_save,button_clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.show_note);
        MyEdit.this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.fortitle);

        edit= (EditText) findViewById(R.id.show_note);
        button_save= (Button) findViewById(R.id.save_change);
        button_clear= (Button) findViewById(R.id.clear_change);
        try {
            readFile("a.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalDialog1();
            }
        });
        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalDialog2();
            }
        });
    }
    public void readFile(String fileName) throws IOException {
        String content=new String();
        FileInputStream fis=openFileInput(fileName);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        byte [] buffer=new byte[1024];
        int len=0;
        while((len=fis.read(buffer))!=-1)
        {
            baos.write(buffer,0,len);
        }
        content=baos.toString();
        edit.setText(content);
        fis.close();
    }
    public void writeFiles(String fileName,String content) throws IOException {
        content=content+"\r\n";
        FileOutputStream fos=openFileOutput(fileName,MODE_ENABLE_WRITE_AHEAD_LOGGING);
        fos.write(content.getBytes());
        fos.close();
    }
    private void showNormalDialog1(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(MyEdit.this);
        normalDialog.setTitle("申明");
        normalDialog.setMessage("你确定修改吗");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            writeFiles("a.txt",edit.getText().toString());
                            Toast.makeText(MyEdit.this,"修改成功",Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        // 显示
        AlertDialog alertDialog = normalDialog.create();
        alertDialog.show();
    }
    private void showNormalDialog2(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(MyEdit.this);
        normalDialog.setTitle("申明");
        normalDialog.setMessage("你确定清空吗");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edit.setText("");
                        try {
                            writeFiles("a.txt","");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(MyEdit.this,"清空成功",Toast.LENGTH_SHORT).show();
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        // 显示
        AlertDialog alertDialog = normalDialog.create();
        alertDialog.show();
    }
}
