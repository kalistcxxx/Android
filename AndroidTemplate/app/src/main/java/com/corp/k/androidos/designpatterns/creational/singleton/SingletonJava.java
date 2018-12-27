package com.corp.k.androidos.designpatterns.creational.singleton;

/**
 * Created by hoangtuan on 7/11/17.
 */

public class SingletonJava {

    private static SingletonJava _instance = new SingletonJava();

    public static synchronized SingletonJava getInstance(){
        if(_instance==null){
            synchronized (SingletonJava.class){
                _instance = new SingletonJava();
            }
        }
        return _instance;
    }

    private SingletonJava(){

    }

    private class DataConnectionSingleton{
//        static DataConnectionSingleton _instance = new DataConnectionSingleton();
//
//        public static DataConnectionSingleton getInstance(){
//            if(_instance==null){
//                _instance = new DataConnectionSingleton();
//            }
//            return _instance;
//        }
//
//        private DataConnectionSingleton(){
//
//        }
    }

    public void doingSomething(){

    }

    public void usingSingleton(){
        SingletonJava.getInstance().doingSomething();
        SingletonJava.getInstance().doingSomething();
    }
}
