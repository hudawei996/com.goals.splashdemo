package com.goals.splashdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;


/**
 * Created by Administrator on 2017/4/24 0024.
 */

public class JumpThread implements Runnable {
    private TextView textView;
    private int numberTime ;
    private Activity activity;

    public JumpThread(TextView textView, int numberTime, Activity activity) {
        this.textView = textView;
        this.numberTime = numberTime;
        this.activity = activity;
    }

    Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            textView.setText("跳转   "+String.valueOf(numberTime));
            if (numberTime==0) {
                jumpActivity();
            }
        }
    };
    private void jumpActivity(){
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        //activity切换动画
        activity.overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);

        activity.finish();
    }
    @Override
    public void run() {
        try {
            while (numberTime>0) {
                Thread.sleep(1000);
                numberTime--;
                handler.sendMessage(new Message());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
