package com.example.administrator.mywenzi1;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity {
    private double j = 0;
    public int i = 0;
    private ImageView imageView, imageView2;
    private TextView textView,textView1;
    private Handler handler;
    public int[][] position = new int[][]{
            {100/2,100},{100/2,200},{100/2,300},{100/2,400},{100/2,500},{100/2,600},{100/2,700},
            {200/2,100},{200/2,200},{200/2,300},{200/2,400},{200/2,500},{200/2,600},{200/2,700},
            {300/2,100},{300/2,200},{300/2,300},{300/2,400},{300/2,500},{300/2,600},{300/2,700},
            {400/2,100},{400/2,200},{400/2,300},{400/2,400},{400/2,500},{400/2,600},{400/2,700},
            {500/2,100},{500/2,200},{500/2,300},{500/2,400},{500/2,500},{500/2,600},{500/2,700},
            {600/2,100},{600/2,200},{600/2,300},{600/2,400},{600/2,500},{600/2,600},{600/2,700},
            {700/2,100},{700/2,200},{700/2,300},{700/2,400},{700/2,500},{700/2,600},{700/2,700},
            {800/2,100},{800/2,200},{800/2,300},{800/2,400},{800/2,500},{800/2,600},{800/2,700},
            {900/2,100},{900/2,200},{900/2,300},{900/2,400},{900/2,500},{900/2,600},{900/2,700},
            {1000/2,100},{1000/2,200},{1000/2,300},{1000/2,400},{1000/2,500},{1000/2,600},{1000/2,700},
            {1100/2,100},{1100/2,200},{1100/2,300},{1100/2,400},{1100/2,500},{1100/2,600},{1100/2,700}
    };
    MediaPlayer mediaPlayer1 = null;
    MediaPlayer mediaPlayer2 = null;
    MediaPlayer mediaPlayer3 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView)findViewById(R.id.textView);
        textView1=(TextView)findViewById(R.id.textView2) ;
        mediaPlayer1 = MediaPlayer.create(this, R.raw.back1);
        mediaPlayer2 = MediaPlayer.create(this, R.raw.flymusic);
        mediaPlayer3 = MediaPlayer.create(this, R.raw.paida6);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.setVisibility(View.VISIBLE);
                i++;
                imageView.setImageResource(R.drawable.pai);
                mediaPlayer3.start();
                return false;
            }
        });
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                int index = 0;
                if (msg.what == 0x101) {
                    j=j+0.5;
                    int k;
                    int l=(int)j;
                    index = msg.arg1;
                    imageView.setX(position[index][0]); // 设置X轴位置
                    imageView.setY(position[index][1]); // 设置Y轴位置
                    imageView.setImageResource(R.drawable.wenzi);//拍后复原
                    imageView.setVisibility(View.VISIBLE); // 设置蚊子显示
                    mediaPlayer2.start();
                    textView.setText("已经消灭了"+i+"只蚊子");
                    if(l==j){
                         k=60-l;
                    textView1.setText("剩余时间："+k);
                    if(k==59){
                        mediaPlayer1.start();
                    }else if(k%10==0){
                        mediaPlayer1.start();
                    }
                    if (k==0){
                        mediaPlayer1.stop();
                        Intent intent=new Intent(MainActivity.this,Stop.class);
                        intent.putExtra("i", i);
                        startActivity(intent);
                    }
                    }
                }
                super.handleMessage(msg);
            }
        };
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                int index = 0; // 创建一个记录蚊子位置的索引值
                while (!Thread.currentThread().isInterrupted()) {
                    index = new Random().nextInt(position.length); // 产生一个随机数
                    Message m = handler.obtainMessage(); // 获取一个Message
                    m.what = 0x101; // 设置消息标识
                    m.arg1 = index; // 保存位置标位置的索引值
                    handler.sendMessage(m); // 发送消息
                    try {
                        Thread.sleep(new Random().nextInt(500) + 500); // 休眠一段时间
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();// 开启线程
        if(j==100){
        }
    }
}


