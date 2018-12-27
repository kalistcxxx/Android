package com.corp.k.androidos.designpatterns.creational.abstracfactory;

public class Server extends Computer {

    public void setRam(String ram) {
        Ram = ram;
    }

    public void setHDD(String HDD) {
        this.HDD = HDD;
    }

    public void setDriver(String driver) {
        Driver = driver;
    }

    private String Ram;
    private String HDD;
    private String Driver;

    public Server(String ram, String hdd, String driver){
        this.Ram = ram;
        this.HDD = hdd;
        this.Driver = driver;
    }

    @Override
    public String getRam() {
        return Ram;
    }

    @Override
    public String getHDD() {
        return HDD;
    }

    @Override
    public String getDriver() {
        return Driver;
    }
}
