package com.corp.k.androidos.designpatterns.structural.composite;

public class Circle implements Shape {

    @Override
    public void draw(String fillColor) {
        String str = "Drawing circle with color: " + fillColor;
    }
}
