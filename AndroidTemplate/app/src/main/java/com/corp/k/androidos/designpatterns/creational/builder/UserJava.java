package com.corp.k.androidos.designpatterns.creational.builder;

/**
 * Created by hoangtuan on 7/12/17.
 */

public class UserJava {

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    private String name;
    private String id;
    private String firstname;
    private String lastname;
    private String address;
    private String phonenumber;

    public UserJava(String mname, String mid, String mfirstname, String mlastname, String maddress, String mphonenumber){
        this.name= mname;
        this.id = mid;
        this.firstname = mfirstname;
        this.lastname = mlastname;
        this.address = maddress;
        this.phonenumber = mphonenumber;
    }
}
