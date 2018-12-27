package com.corp.k.androidos.designpatterns.creational.abstracfactory;

public class ComputerFactory {

    public static Computer getComputer(ComputerAbstractFactory factory){
        return factory.createComputer();
    }
}
