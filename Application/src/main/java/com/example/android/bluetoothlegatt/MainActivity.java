package com.example.android.bluetoothlegatt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



 /*       intent=new Intent(this,LoginActivity.class);

       intent.putExtra("school_code",code);
       this.startActivity(intent);

       인텐트로 받으면  로그인 액티비티에서 mainFragment를 띄우지 못함
*/
}
}
