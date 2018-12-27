package com.corp.k.androidos.designpatterns.creational.abstracfactory;

public abstract class Computer {

    public abstract String getRam();
    public abstract String getHDD();
    public abstract String getDriver();

    @Override
    public String toString(){
        return "RAM = " + getRam() + ", HDD = " + getHDD() + ", Driver = " + getDriver();
    }
}
