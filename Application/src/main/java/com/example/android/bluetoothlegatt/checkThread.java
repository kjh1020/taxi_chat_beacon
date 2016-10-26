package com.example.android.bluetoothlegatt;

import android.os.Message;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by user on 2016-10-16.
 */
public class checkThread extends Thread  {
    private ArrayList<Beacon> b_list=BleSingleList.getList();
    private Mhandler handler;
    public checkThread(Mhandler handler){
       this.handler = handler;
   }

    static int count =0;
    @Override
    public void run() {


        LinkedList ls=SingleQueue.getList();

            String addr = ls.poll().toString();
            String[] addr_first = addr.split("-");
            String addr_check=addr_first[0];
            String rssi_str=addr_first[1];
            int rssi= Integer.parseInt(rssi_str);



            for (Beacon ble : b_list) {
                if (ble.getAddress().equals(addr_check)) {
                    if(rssi<80) {
                        SingleList.add(ble.getAddress());
                    }
                }
            }
        Log.e("사이즈 확인"+SingleList.getList().size(),"dd");



        Message msg = new Message();
        if(SingleList.getList().size()>0) {

            msg.what = ref.MSG_CHECK;
            handler.sendMessage(msg);
            this.interrupt();

        }else{

            count++;
            if(count>5) {
                msg.what = ref.MSG_CANCEL;
                handler.sendMessage(msg);
            }

        }
    }
}
