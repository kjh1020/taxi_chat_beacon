package com.example.android.bluetoothlegatt;

import android.renderscript.ScriptC;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by user on 2016-10-15.
 */
class ThreadSc extends Thread  {
    StringBuilder sb;
    String json;
    List<String> data;

    public void run() {
        BleSingleList.getInstance();
        sb = new StringBuilder();
        try {

            String addr = "http://61.84.24.245:52274/school";
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

                Type collectionType = new TypeToken<List<School>>() {
                }.getType();
                List<School> lcs = (List<School>) new Gson()
                        .fromJson(json, collectionType);



                if(SchSingleList.getList().size()==0){
                    for (School sc : lcs) {
                        SchSingleList.add(sc);
                    }
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





