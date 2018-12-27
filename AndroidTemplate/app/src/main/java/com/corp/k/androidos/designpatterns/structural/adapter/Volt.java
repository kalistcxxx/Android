package com.corp.k.androidos.designpatterns.structural.adapter;

public class Volt {

    public int getVolt() {
        return volt;
    }

    public void setVolt(int volt) {
        this.volt = volt;
    }

    private int volt;

    public Volt(int in){
        this.volt = in;
    }
}
