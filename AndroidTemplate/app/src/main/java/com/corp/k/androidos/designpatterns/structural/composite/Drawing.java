package com.corp.k.androidos.designpatterns.structural.composite;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Drawing implements Shape {


    public ArrayList<Shape> getListShape() {
        return listShape;
    }

    public void setListShape(ArrayList<Shape> listShape) {
        this.listShape = listShape;
    }

    private ArrayList<Shape> listShape = new ArrayList<>();

    public Drawing(){}

    @Override
    public void draw(String fillColor) {
        for(Shape p : listShape){
            p.draw(fillColor);
        }
    }

    public void addShape(Shape a){
        if(!listShape.contains(a)) listShape.add(a);
    }

    public void removeShape(Shape a){
        if(listShape.contains(a)) listShape.remove(a);
    }

    public void clear(){
        if(listShape.size()>0) listShape.clear();
    }
}
