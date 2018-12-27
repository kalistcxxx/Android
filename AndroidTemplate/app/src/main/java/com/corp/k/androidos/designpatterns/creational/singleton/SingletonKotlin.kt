package com.kal.company.mykotlin.DesignPatterns.Creational.Singleton.Kotlin

/**
 * Created by hoangtuan on 7/11/17.
 */

object DatabaseConnectionSingleton{
    init {
        print("Initializing with object")
    }

    fun openConnection(){
        print("Starting opening connection")
    }
}

class UsingSingleton{
    fun runable(){
        DatabaseConnectionSingleton.openConnection();
        DatabaseConnectionSingleton.openConnection();
    }
}