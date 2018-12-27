package com.corp.k.androidos.designpatterns.creational.prototype;

import java.util.ArrayList;

public class Prototype implements Cloneable {

    public ArrayList<String> getListAtts() {
        return listAtts;
    }

    public void setListAtts(ArrayList<String> listAtts) {
        this.listAtts = listAtts;
    }

    private ArrayList<String> listAtts;

    public Prototype(){
        listAtts = new ArrayList<>();
    }

    public Prototype(ArrayList<String> list){
        this.listAtts = list;
    }

    public void loadData(){
        listAtts.add("A");
        listAtts.add("B");
        listAtts.add("C");
        listAtts.add("D");
        listAtts.add("E");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        super.clone();
        ArrayList<String> cloneData = new ArrayList<>();
        for(String s : listAtts){
            cloneData.add(s);
        }
        return new Prototype(cloneData);
    }
}
