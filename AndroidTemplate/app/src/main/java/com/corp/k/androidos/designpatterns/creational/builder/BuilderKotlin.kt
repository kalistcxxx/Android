package com.corp.k.androidos.designpatterns.creational.builder

/**
 * Created by hoangtuan on 7/11/17.
 */
class User(){
    fun setName(text: String) = println("Set Name")

    fun setId(text: String) = print("Set Id")

    fun setFirstName(text: String) = print("Set First Name")

    fun setLastName(text: String) = print("Set Last Name")

    fun setAddress(text: String) = print("Set Address")

    fun setPhoneNumber(text: String) = print("Set phone number")
}

//Builder
class UserBuilder(){
    constructor(init: UserBuilder.() -> Unit): this(){
        init()
    }

    private var Name: String?=null
    private var Id: String?=null
    private var FirstName: String?=null
    private var LastName: String?=null
    private var Address: String?=null
    private var PhoneNumber: String?=null

    fun name(text: String) : UserBuilder {
        Name = text;
        return this
    }

    fun Id(text: String) : UserBuilder {
        Id = text;
        return this
    }

    fun FirstName(text: String) : UserBuilder {
        FirstName = text;
        return this
    }

    fun Lastname(text: String) : UserBuilder {
        LastName = text;
        return this
    }

    fun Address(text: String) : UserBuilder {
        Address = text;
        return this
    }

    fun PhoneNumber(text: String) : UserBuilder {
        PhoneNumber = text;
        return this
    }

    fun build(): User {
        val user = User()
        user.setName(Name!!)
        user.setId(Id!!)
        user.setFirstName(FirstName!!)
        user.setLastName(LastName!!)
        user.setAddress(Address!!)
        user.setPhoneNumber(PhoneNumber!!)
        return user
    }
}
fun dialog(init: UserBuilder.() -> Unit): User {
    return UserBuilder(init).build()
}
fun usingBuilder(){
    val user = UserBuilder().Id("00001").FirstName("Tuan").Lastname("Hoang").name("Hoang Tuan").build()
}