package com.example.android.bluetoothlegatt;

import java.util.ArrayList;

/**
 * Created by user on 2016-10-05.
 */
public class Beacon {
    private ArrayList<Beacon> bleList;
    private int no;
    private String name;
    private String address;
    private String location;

    private int major;
    private int minor;

    public ArrayList<Beacon> getBleList() {
        return bleList;
    }

    public void setBleList(ArrayList<Beacon> bleList) {
        this.bleList = bleList;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public int getMinor() {
        return minor;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
