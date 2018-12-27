package com.corp.k.androidos.designpatterns.creational.abstracfactory;

public class PCFactory implements ComputerAbstractFactory {

    private String Ram;
    private String HDD;
    private String Driver;

    public PCFactory(String ram, String hdd, String driver){
        this.Ram = ram;
        this.HDD = hdd;
        this.Driver = driver;
    }

    @Override
    public Computer createComputer() {
        return new PC(Ram, HDD, Driver);
    }
}
