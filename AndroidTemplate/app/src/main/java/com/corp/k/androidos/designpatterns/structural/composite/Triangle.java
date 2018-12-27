package com.corp.k.androidos.designpatterns.structural.composite;

public class Triangle implements Shape {

    public Triangle(){

    }

    @Override
    public void draw(String fillColor) {
        String str = "Drawing triangle with color: " + fillColor;
    }
}
