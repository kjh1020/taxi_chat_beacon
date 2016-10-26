package com.example.android.bluetoothlegatt;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by user on 2016-10-15.
 */
public class Mhandler extends Handler {
    Context context;
    int i=0;

    public Mhandler (Context context){
        this.context=context;
    }


    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);

        switch (msg.what)
        {
            case ref.MSG_UPDATE:
                checkThread thr=new checkThread(this);
                thr.start();


                break;
            case ref.MSG_CHECK:
                //서버로 디바이스 고유 번호와 학교코드


                ThreadSc sc = new ThreadSc();
                sc.start();

                try {
                    sc.join();
                    Intent intent = new Intent(context,MainActivity.class);
                    context.startActivity(intent);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }




                break;
            case ref.MSG_CANCEL:
                i++;
                if(i>3) {
                    Toast.makeText(context, "비콘이 감지되지 않습니다.", Toast.LENGTH_SHORT).show();
                }
                break;

            case ref.MSG_RSSI:
                Log.e("RSSI -80보다 작습니다.","test");
                break;


            default:
                break;
        }

    }


}
