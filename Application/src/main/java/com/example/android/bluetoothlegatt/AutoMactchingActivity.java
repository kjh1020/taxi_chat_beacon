package com.example.android.bluetoothlegatt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.util.ArrayList;

import io.socket.client.Socket;

public class AutoMactchingActivity extends Activity {
    private Spinner spn_spinner = null;
    private Button btn = null;



    ArrayList<School> alist;
    MyAdapter adapter1;
    Socket mSocket;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_mactching);


        final TextView tv = (TextView) findViewById(R.id.textView);
        spn_spinner = (Spinner) findViewById(R.id.spinner);
        btn = (Button) findViewById(R.id.button);
        for (School sc : SchSingleList.getList()) {
            Log.e("확인", sc.getCode() + sc.getName());
        }
        alist = SchSingleList.getList();
        adapter1 = new MyAdapter(this, R.layout.item, alist);

        final int[] pos = new int[1];
        spn_spinner.setAdapter(adapter1);

        spn_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("선택된 포지션"+position);
                pos[0] =position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //final String androidId = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                School item=(School)adapter1.getItem(pos[0]);
                System.out.println("선택된 포지션의 값"+item.getCode());

                ChatApplication app=(ChatApplication)getApplication();

                mSocket= app.getSocket();
                mSocket.emit("code",item.getCode());// 학교 코드와 디바이스 고유 번호를 소켓에 보냄



                Intent intent= new Intent(AutoMactchingActivity.this ,MainActivity.class);



                AutoMactchingActivity.this.startActivity(intent);


            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        SchSingleList.clear();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "AutoMactching Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.android.bluetoothlegatt/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "AutoMactching Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.android.bluetoothlegatt/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
