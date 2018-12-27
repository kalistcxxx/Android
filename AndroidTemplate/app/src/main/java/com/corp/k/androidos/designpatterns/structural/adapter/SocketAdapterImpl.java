package com.corp.k.androidos.designpatterns.structural.adapter;

public class SocketAdapterImpl extends Socket implements SocketAdapter {

    @Override
    public Volt get120Volt() {
        return getVolts();
    }

    @Override
    public Volt get12Volt() {
        Volt v = getVolts();
        return convertVolt(v , 10);
    }

    @Override
    public Volt get3Volt() {
        Volt v = getVolts();
        return convertVolt(v , 40);
    }

    private Volt convertVolt(Volt v, int i) {
        return new Volt(v.getVolt()/i);
    }
}
