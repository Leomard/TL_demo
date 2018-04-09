package com.jzone.tl_demo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.algebra.sdk.API;
import com.algebra.sdk.AccountApi;
import com.algebra.sdk.ChannelApi;
import com.algebra.sdk.OnChannelListener;
import com.algebra.sdk.OnMediaListener;
import com.algebra.sdk.OnSessionListener;
import com.algebra.sdk.SessionApi;
import com.algebra.sdk.entity.Channel;
import com.algebra.sdk.entity.Contact;
import com.algebra.sdk.entity.HistoryRecord;
import com.algebra.sdk.entity.Session;
import com.jzone.tl_demo.zxing.MyFragmentDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.jzone.tl_demo.R.id.textView_disu;
import static com.jzone.tl_demo.zxing.encoding.EncodingUtils.createQRCode;

/**
 * Created by 28537 on 2017/7/31.
 */

public class MyAnchor extends Activity {
    private AccountApi accountApi;
    private ChannelApi channelApi;
    private SessionApi sessionApi;
    private Contact contact;
    private Bitmap bitmap;
    private ImageView imagespeak;
    private TextView textview1,textview2,textview3,textview4;
    private TextView text_disu,whoToSay;
    private Bundle bundle;
    private ToggleButton toggleButton;
    private ImageView imageView1,imageView2,imagestate,imageMenu;
    private Session session;
    private int s;
    private String cname,nickName;
    private int cidId,cidType,cMem,cIni,cAnchorId;
    private List<Contact> mem;
    private EditText addText;
    private Button clear,save;
    private int nowId;
    private Boolean ispassword;
    private int flag;
    private Boolean listsignal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.my_channel);
        MyAnchor.this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.fortitle);
        mem=new ArrayList<Contact>();
        bundle = this.getIntent().getExtras();
        listsignal=false;
        cname=bundle.getString("Name");
        cMem=bundle.getInt("member");
        cidId=bundle.getInt("cidId");
        cidType=bundle.getInt("cidType");
        nickName=bundle.getString("nickName");
        cAnchorId=bundle.getInt("anchorId");
        ispassword=bundle.getBoolean("ispassword");
        flag=0;
        imagespeak= (ImageView) findViewById(R.id.image_speaking);
        imagespeak.setImageResource(R.drawable.channel_seek_background);
        text_disu= (TextView) findViewById(textView_disu);
        toggleButton= (ToggleButton) findViewById(R.id.my_ptt);
        imageView1= (ImageView) findViewById(R.id.my_ptt_spk_ind);
        imageView2= (ImageView) findViewById(R.id.my_ptt_mic_ind);
        whoToSay= (TextView) findViewById(R.id.whotosay);
        imagestate= (ImageView) findViewById(R.id.image_state);
        imagestate.setImageResource(R.drawable.user_online);
        imageMenu= (ImageView) findViewById(R.id.imageView8);
        imageView1.setSelected(false);
        imageView1.setPressed(false);
        imageView2.setSelected(false);
        imageView2.setPressed(false);
        toggleButton.setChecked(false);
        toggleButton.setSelected(false);
        textview1= (TextView) findViewById(R.id.textView86);
        textview2= (TextView) findViewById(R.id.textView88);
        textview3= (TextView) findViewById(R.id.textView82);
        textview4= (TextView) findViewById(R.id.textView84);
        addText= (EditText) findViewById(R.id.add_content);
        clear= (Button) findViewById(R.id.button_clear);
        save= (Button) findViewById(R.id.button_save);
        textview3.setText(cname);
        textview4.setText(String.valueOf(cMem+1));
        textview1.setText(nickName);
        textview2.setText("主播开播");
        text_disu.setText("听众时间");
        accountApi= API.getAccountApi();
        channelApi=API.getChannelApi();
        sessionApi=API.getSessionApi();
        contact=accountApi.whoAmI();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalDialog1();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalDialog2();
            }
        });
        imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showListDialog();
            }
        });
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true)
                {
                    imageView1.setSelected(true);
                    imageView1.setPressed(true);
                    imageView2.setSelected(true);
                    imageView2.setPressed(true);
                    toggleButton.setChecked(true);
                    toggleButton.setSelected(true);
                    Log.i("PTT", "DOWN " );
                    sessionApi.talkRequest(contact.id,cidType,cidId);
                }
                else
                {
                    imageView1.setSelected(false);
                    imageView1.setPressed(false);
                    imageView2.setSelected(false);
                    imageView2.setPressed(false);
                    toggleButton.setChecked(false);
                    toggleButton.setSelected(false);
                    Log.i("PTT", "UP" );
                    sessionApi.talkRelease(contact.id,cidType,cidId);
                }
            }
        });
       /* toggleButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN)
                {
                    imageView1.setSelected(true);
                    imageView1.setPressed(true);
                    imageView2.setSelected(true);
                    imageView2.setPressed(true);
                    toggleButton.setChecked(true);
                    toggleButton.setSelected(true);
                    Log.i("PTT", "DOWN " );
                    sessionApi.talkRequest(contact.id,cidType,cidId);
                }
                else if(event.getAction()==MotionEvent.ACTION_UP)
                {
                    imageView1.setSelected(false);
                    imageView1.setPressed(false);
                    imageView2.setSelected(false);
                    imageView2.setPressed(false);
                    toggleButton.setChecked(false);
                    toggleButton.setSelected(false);
                    Log.i("PTT", "UP" );
                    sessionApi.talkRelease(contact.id,cidType,cidId);
                }
                return false;
            }
        });
            */
        sessionApi.setOnSessionListener(new OnSessionListener() {
            @Override
            public void onSessionEstablished(int i, int i1, int i2) {

            }

            @Override
            public void onSessionReleased(int i, int i1, int i2) {
                if(i>0)
                {
                    Toast.makeText(MyAnchor.this,"退出会话成功",Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent();
                    intent.setClass(MyAnchor.this,MyNewTab.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    MyAnchor.this.startActivity(intent);
                }
                else
                {
                    Toast.makeText(MyAnchor.this,"退出会话失败",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onSessionGet(int i, int i1, int i2, int i3) {

            }

            @Override
            public void onSessionPresenceAdded(int i, int i1, List<Contact> list) {
                /*for(int k=0;k<list.size();k++)
                {
                    int z=0;
                    for (int j = 0; j < mem.size(); j++)
                    {
                        if (list.get(k).id==mem.get(j).id)
                        {
                            z = 1;
                            break;
                        }
                    }
                    if(z!=1)
                    {
                        Toast.makeText(MyAnchor.this,list.get(k).name+" 加入会话",Toast.LENGTH_SHORT).show();
                        cMem+=1;
                        break;
                    }
                }

                textview4.setText(String.valueOf(cMem));*/
                for(int k=0;k<list.size();k++)
                    Toast.makeText(MyAnchor.this,list.get(k).name+" 加入会话",Toast.LENGTH_SHORT).show();
                cMem+=list.size();
                textview4.setText(String.valueOf(cMem));
            }

            @Override
            public void onSessionPresenceRemoved(int i, int i1, List<Integer> list) {
               /* for(int k=0;k<mem.size();k++)
                {
                    int z=0;
                    for (int j = 0; j < list.size(); j++)
                    {
                        if (list.get(k)==mem.get(j).id)
                        {
                            z = 1;
                            break;
                        }
                    }
                    if(z!=1)
                    {
                        Toast.makeText(MyAnchor.this,mem.get(k).name+" 退出会话",Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                textview4.setText(String.valueOf(list.size()));
                */
                for(int k=0;k<list.size();k++)
                {
                    Toast.makeText(MyAnchor.this,list.get(k)+" 退出会话",Toast.LENGTH_SHORT).show();
                }
                cMem-=list.size();
                textview4.setText(String.valueOf(cMem));
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
        sessionApi.setOnMediaListener(new OnMediaListener() {
            @Override
            public void onMediaInitializedEnd(int i, int i1, int i2) {

            }

            @Override
            public void onPttButtonPressed(int i, int i1) {

            }

            @Override
            public void onTalkRequestConfirm(int i, int i1, int i2, int i3, boolean b) {
                imagestate.setImageResource(R.drawable.user_self);
                text_disu.setText("主播正讲");
                imagespeak.setImageResource(R.drawable.talking1);
                nowId=i;
                channelApi.channelMemberGet(contact.id,cidType,cidId);
            }

            @Override
            public void onTalkRequestDeny(int i, int i1, int i2) {
                imagestate.setImageResource(R.drawable.user_busy);
            }

            @Override
            public void onTalkRequestQueued(int i, int i1, int i2) {
                imagestate.setImageResource(R.drawable.user_presence);
            }

            @Override
            public void onTalkReleaseConfirm(int i, int i1)
            {
                text_disu.setText("听众时间");
                imagespeak.setImageResource(R.drawable.channel_seek_background);
                imagestate.setImageResource(R.drawable.user_online);
                whoToSay.setText("");
            }

            @Override
            public void onTalkTransmitBroken(int i, int i1) {

            }

            @Override
            public void onStartPlaying(int i, int i1, int i2, int i3) {

            }

            @Override
            public void onPlayStopped(int i) {

            }

            @Override
            public void onSomeoneSpeaking(int i, int i1, int i2, int i3, int i4) {
                if(i==cAnchorId)
                {
                    text_disu.setText("主播正讲");
                }
                imagespeak.setImageResource(R.drawable.talking1);
                nowId=i;
                if(!listsignal)
                {
                    channelApi.channelMemberGet(contact.id,cidType,cidId);
                    listsignal=true;
                }
                Log.i("有人讲话","成功");
            }

            @Override
            public void onThatoneSayOver(int i, int i1) {
                if(i==cAnchorId)
                {
                    text_disu.setText("听众时间");
                }
                listsignal=false;
                imagespeak.setImageResource(R.drawable.channel_seek_background);
                whoToSay.setText("");
                Log.i("有人讲话","退出");
            }

            @Override
            public void onSomeoneAttempt(int i, int i1, int i2) {

            }

            @Override
            public void onThatAttemptQuit(int i, int i1, int i2) {

            }

            @Override
            public void onNewSpeakingCatched(HistoryRecord historyRecord) {

            }

            @Override
            public void onPlayLastSpeaking(int i, int i1) {

            }

            @Override
            public void onPlayLastSpeakingEnd(int i) {

            }

            @Override
            public void onMediaSenderCutted(int i, int i1) {

            }

            @Override
            public void onMediaSenderReport(long l, int i, int i1, int i2, int i3) {

            }

            @Override
            public void onMediaReceiverReport(long l, int i, int i1, int i2, int i3) {

            }

            @Override
            public void onRecorderMeter(int i, int i1) {

            }

            @Override
            public void onPlayerMeter(int i, int i1) {

            }

            @Override
            public void onBluetoothBatteryGet(int i) {

            }

            @Override
            public void onBluetoothConnect(int i) {

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
                if(flag==1) {
                    flag=0;
                    MyFragmentDialog myFragmentDialog = new MyFragmentDialog();
                    Bundle bundle = new Bundle();
                    bundle.putInt("size", list.size());
                    List<String> stringList = new ArrayList<String>();
                    for (int k = 0; k < list.size(); k++) {
                        stringList.add(list.get(k).name);
                    }
                    bundle.putStringArrayList("name", (ArrayList<String>) stringList);
                    myFragmentDialog.setArguments(bundle);
                    myFragmentDialog.show(getFragmentManager(), "DialogFragment");

                }
                else {
                    for(int k=0;k<list.size();k++)
                    {
                        if(list.get(k).id==nowId)
                        {
                            whoToSay.setText(list.get(k).name);
                            break;
                        }
                    }
                }
            }

            @Override
            public void onChannelNameChanged(int i, int i1, int i2, String s) {
                if(i>0)
                {
                    Toast.makeText(MyAnchor.this,"名字修改成功",Toast.LENGTH_SHORT).show();
                    textview3.setText(s);
                }
                else
                {
                    Toast.makeText(MyAnchor.this,"名字修改失败",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onChannelAdded(int i, int i1, int i2, String s) {

            }

            @Override
            public void onChannelRemoved(int i, int i1, int i2) {
                if(i>0)
                {
                    Toast.makeText(MyAnchor.this,"频道被移除成功",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent().setClass(MyAnchor.this,MyNewTab.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    MyAnchor.this.startActivity(intent);
                }
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
                if(i>0)
                {
                    Toast.makeText(MyAnchor.this,"取消关注成功",Toast.LENGTH_SHORT).show();
                    sessionApi.sessionBye(contact.id,cidType,cidId);
                }
                else
                    Toast.makeText(MyAnchor.this,"取消关注失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPubChannelRenamed(int i, int i1) {

            }

            @Override
            public void onPubChannelDeleted(int i, int i1) {
                if(i>0)
                {
                    Toast.makeText(MyAnchor.this,"频道被移除成功",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent().setClass(MyAnchor.this,MyNewTab.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    MyAnchor.this.startActivity(intent);
                }
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
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sessionApi.sessionBye(contact.id,cidType,cidId);
    }


    private void showListDialog() {
        final String[] items = { "频道改名","删除频道","生成二维码","查看成员列表","取消" };
        Builder listDialog =
                new Builder(this);
        listDialog.setTitle("操作");
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // which 下标从0开始
                // ...To-do
                if(which==0)
                {
                    showInputDialog();
                }
                else if(which==1)
                {
                    showNormalDialog();
                }
                else if(which==2)
                {
                    String s;
                    s=new String();
                    s="";
                    s=String.valueOf(cidType)+"#"+String.valueOf(cidId)+"#"+String.valueOf(ispassword);
                    create(s);
                }
                else if(which==3)
                {
                    channelApi.channelMemberGet(contact.id,cidType,cidId);
                    flag=1;
                }
            }
        });
        AlertDialog alertDialog = listDialog.create();
        alertDialog.show();
    }
    private void showInputDialog() {
    /*@setView 装入一个EditView
     */
        final EditText editText = new EditText(this);
        Builder inputDialog =
                new Builder(this);
        inputDialog.setTitle("输入需要改为的名字").setView(editText);
        inputDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        channelApi.changePublicChannelName(contact.id,cidType,cidId,editText.getText().toString());
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
    private void showNormalDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final Builder normalDialog =
                new Builder(MyAnchor.this);
        normalDialog.setTitle("申明");
        normalDialog.setMessage("你确定删除该频道吗");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        channelApi.deletePublicChannel(contact.id,cidType,cidId);
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        AlertDialog alertDialog = normalDialog.create();
        alertDialog.show();
    }
    public void writeFiles(String fileName,String content) throws IOException {
        SimpleDateFormat sDateFormat    =   new    SimpleDateFormat("yyyy-MM-dd    hh:mm:ss");
        String    date    =    sDateFormat.format(new    java.util.Date());
        content=date+"\r\n"+content+"\r\n";
        FileOutputStream fos=openFileOutput(fileName,MODE_APPEND);
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
                new AlertDialog.Builder(MyAnchor.this);
        normalDialog.setTitle("申明");
        normalDialog.setMessage("你确定修改吗");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            writeFiles("a.txt",addText.getText().toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(MyAnchor.this,"保存成功",Toast.LENGTH_SHORT).show();
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
                new AlertDialog.Builder(MyAnchor.this);
        normalDialog.setTitle("申明");
        normalDialog.setMessage("你确定清空吗");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addText.setText("");
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
    private void create(String s) {
        int width = 500;

        bitmap = createQRCode(s,
                width, width, BitmapFactory.decodeResource(getResources(),R.drawable.myicon));
        imageDialog();
    }

    /**
     * 将Bitmap保存在本地
     *
     *
     */
    private void imageDialog()
    {
    /*@setView 装入一个EditView
     */
        ImageView editText=new ImageView(this);
        editText.setAdjustViewBounds(true);
        editText.setMaxHeight(1000);
        editText.setMaxWidth(1000);


        AlertDialog.Builder inputDialog =
                new AlertDialog.Builder(this);
        inputDialog.setPositiveButton("保存", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                saveBitmap(bitmap);
            }
        })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = inputDialog.create();
        editText.setImageBitmap(bitmap);
        alertDialog.setView(editText,200,200,200,200);
        alertDialog.show();

    }
    public void saveBitmap(Bitmap bitmap) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(),
                "zxing_image");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "zxing_image" + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(this.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 通知图库更新
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.parse("file://" + "/sdcard/namecard/")));
    }
}

