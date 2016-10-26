package com.example.android.bluetoothlegatt;

import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by user on 2016-10-14.
 */
public class Beacon_admin {

    private ArrayList<Beacon> ble_list; // 스캔되는 Ble 기기들을 담는 리스트
    private ArrayList<Beacon> ble_white_list; // 스캔되는 Ble 기기들을 비교할 화이트리스트








    public static boolean compareList(HashSet set, ArrayList ls2){// 리스트 비교하는 메소드



        return false;
    }
    public boolean connectServer(){// true 면 UI단에 자동매칭 버튼이 보이게 코딩할예정


        return false;
    }
    public void cancelconnect(){//핸드폰이 해당 비콘을 감지한 후 그 비콘의 세기가 특정세기 이하로 되었을때 서버로부터 disconnect될 수 있도록한다.

    }
    public void automatching(String phone_id){

    }
}



