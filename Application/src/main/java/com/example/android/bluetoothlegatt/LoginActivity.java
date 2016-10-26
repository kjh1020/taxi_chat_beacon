package com.example.android.bluetoothlegatt;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;


/**
 * A login screen that offers login via username.
 */
public class LoginActivity extends Activity {

    private EditText mUsernameView;

    private String mUsername;

    private Socket mSocket;


    private Spinner spn_spinner = null;

    ArrayList<School> alist;
    MyAdapter adapter1;
    final int[] pos = new int[1];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        ChatApplication app = (ChatApplication) getApplication();
        mSocket = app.getSocket();

        // Set up the login form.
        mUsernameView = (EditText) findViewById(R.id.username_input);
        mUsernameView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button signInButton = (Button) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                attemptLogin();


            }
        });



        mSocket.on("login", onLogin);



        final TextView tv = (TextView) findViewById(R.id.textView1);
        spn_spinner = (Spinner) findViewById(R.id.spinner1);

        for (School sc : SchSingleList.getList()) {
            Log.e("확인", sc.getCode() + sc.getName());
        }
        alist = SchSingleList.getList();
        adapter1 = new MyAdapter(this, R.layout.item, alist);


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

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        mSocket.off("login", onLogin);
    }

    /**
     * Attempts to sign in the account specified by the login form.
     * If there are form errors (invalid username, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        School item=(School)adapter1.getItem(pos[0]);
        System.out.println("선택된 포지션의 값"+item.getCode());

        mSocket.emit("code",item.getCode());// 학교 코드와 디바이스 고유 번호를 소켓에 보냄


        // Reset errors.
        mUsernameView.setError(null);

        // Store values at the time of the login attempt.
        String username = mUsernameView.getText().toString().trim();

        // Check for a valid username.
        if (TextUtils.isEmpty(username)) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            mUsernameView.setError(getString(R.string.error_field_required));
            mUsernameView.requestFocus();
            return;
        }

        mUsername = username;

        // perform the user login attempt.
        mSocket.emit("add user", username);


          /*
        클라이언트 별로 랜덤하게 묶어 채팅할 수 있도록  아래는 가라로 만듬

        String[] str ={"s002","s001"};
        int a= (int) Math.floor(Math.random()*2);
        System.out.println("test : "+str[a]);
        mSocket.emit("join", str[a]);
        */
        //mSocket.emit("join", code); 에러 뜸... 넘어가질 않음

    }

    private Emitter.Listener onLogin = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONObject data = (JSONObject) args[0];

            int numUsers;
            try {
                numUsers = data.getInt("numUsers");

            } catch (JSONException e) {

                return;
            }

            Intent intent = new Intent();
            intent.putExtra("username", mUsername);
            intent.putExtra("numUsers", numUsers);
            setResult(RESULT_OK, intent);
            finish();
        }
    };


}



