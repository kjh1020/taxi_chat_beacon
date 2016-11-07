package com.example.android.bluetoothlegatt;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.example.android.bluetoothlegatt.SingleQueue.*;

/**
 * Created by user on 2016-10-15.
 */
class ThreadEx extends Thread  {
    StringBuilder sb;
    String json;
    List<String> data;

    public void run() {
        BleSingleList.getInstance();
        sb = new StringBuilder();
        try {

            String addr = "http://61.84.24.245:52274/beacon";
            URL url = new URL(addr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            if (con != null) {
                con.setUseCaches(false);
                con.setConnectTimeout(10000);
                if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = con.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                    while (true) {
                        String line = br.readLine();
                        if (line == null) {
                            break;
                        }
                        sb.append(line);


                    }
                    br.close();
                    con.disconnect();
                }
            }
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
        }
        try {
            if (json != null) {
                Log.e("json", json);
                Gson gson = new Gson();

                Type collectionType = new TypeToken<List<Beacon>>() {
                }.getType();
                List<Beacon> lcs = (List<Beacon>) new Gson()
                        .fromJson(json, collectionType);

                for (Beacon ble : lcs) {
                    BleSingleList.add(ble);
                }
            }
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
        }


    }

}

//    @Override
//    public  void compareList(final ArrayList<Beacon> list) {
//
//        LinkedList ls=SingleQueue.getList();
//        String addr=ls.poll().toString();
//         for(Beacon ble : list){
//           if(ble.getAddress().equals(addr)) {
//               SingleList.add(ble.getAddress());
//
//                }
//             }
//
//
//
//         }
//};





