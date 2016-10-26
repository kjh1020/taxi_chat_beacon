package com.example.android.bluetoothlegatt;

import android.os.Message;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by user on 2016-10-16.
 */
public class RssicheckThread extends Thread  {
    private ArrayList<Beacon> b_list=BleSingleList.getList();
    private Mhandler handler;
    public RssicheckThread(Mhandler handler){
       this.handler = handler;
   }


    @Override
    public void run() {
        boolean check =false;

        LinkedList ls=SingleRssiQueue.getList();

            String rssi_str =ls.poll().toString();
            String rssi_s=rssi_str.replace("-","");

           int rssi = Integer.parseInt(rssi_s);

               if(rssi < 80) {
                  check = true;
                }



        Message msg = new Message();
        if(check) {
            msg.what = ref.MSG_RSSI;
            handler.sendMessage(msg);
        }
    }
}
