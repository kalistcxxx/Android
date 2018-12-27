package com.corp.k.androidos.designpatterns.creational.builder;

/**
 * Created by hoangtuan on 7/12/17.
 */

public class JavaBuilder {

    private String name="";
    private String id="";
    private String firstname="";
    private String lastname="";
    private String address="";
    private String phonenumber="";

    public JavaBuilder(){

    }

    public JavaBuilder name(String sname){
        this.name = sname;
        return this;
    }

    public JavaBuilder id(String sid){
        this.id = sid;
        return this;
    }

    public JavaBuilder firstname(String sfirstname){
        this.firstname = sfirstname;
        return this;
    }

    public JavaBuilder lastname(String slastname){
        this.lastname = slastname;
        return this;
    }

    public JavaBuilder address(String saddress){
        this.address = saddress;
        return this;
    }

    public JavaBuilder phonenumber(String sphonenumber){
        this.phonenumber = sphonenumber;
        return this;
    }

    public UserJava build(){
        return new UserJava(name, id, firstname, lastname, address, phonenumber);
    }

    public static JavaBuilder createUser(){
        return new JavaBuilder();
    }


    public void usingBuilder(){
        UserJava user = JavaBuilder.createUser().name("Tuan").address("Ha Noi").build();
    }

}
