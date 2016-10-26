package com.example.android.bluetoothlegatt;

import java.util.ArrayList;

/**
 * Created by user on 2016-10-05.
 */
public class School {
    private ArrayList<School> schList;
    private int no;
    private String code;

    public ArrayList<School> getSchList() {
        return schList;
    }

    public void setSchList(ArrayList<School> schList) {
        this.schList = schList;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

}
