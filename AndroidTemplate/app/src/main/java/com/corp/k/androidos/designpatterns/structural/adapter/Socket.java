package com.corp.k.androidos.designpatterns.structural.adapter;

public class Socket {
    public Volt getVolts(){
        return new Volt(120);
    }
}
