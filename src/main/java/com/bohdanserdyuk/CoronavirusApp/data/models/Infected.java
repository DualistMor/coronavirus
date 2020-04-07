package com.bohdanserdyuk.CoronavirusApp.data.models;

import java.io.Serializable;

public class Infected implements Serializable {
    private String phoneNumber;
    private String name;
    private String address;

    public Infected() {
    }

    public Infected(String phoneNumber, String name, String address) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
