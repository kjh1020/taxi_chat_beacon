package com.example.android.bluetoothlegatt;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;


public class ServiceThread extends Thread {
    private Mhandler handler;
    private ArrayList<Beacon> ble_list;
    Beacon bea=new Beacon();
    boolean isRun = true;
    private  BluetoothDevice device;
    private int rssi;



    public ServiceThread(Mhandler handler, final BluetoothDevice device, final int rssi){
        this.handler = handler;
        this.device=device;
        this.rssi=rssi;
    }

    public void stopForever(){
        synchronized (this) {
            this.isRun = false;
        }
    }

    public void run(){



        SingleQueue.getList().addLast(device.getAddress()+rssi);
        SingleRssiQueue.getList().addLast(rssi);
        /*
           검증


         */






        Message msg = new Message();
        msg.what=ref.MSG_UPDATE;

        handler.sendMessage(msg);





    }
}