package com.corp.k.androidos.designpatterns.creational.factory;

import android.util.Log;

/**
 * Created by hoangtuan on 7/11/17.
 */

public class FactoryJavaClass {
    interface DatabaseConnection{
        public void openConnection();
        public String getCode();
    }

    //Class A
    private class MySQL implements DatabaseConnection{
        private String code;
        MySQL(String codeString){
            code = codeString;
        }

        @Override
        public void openConnection() {
            Log.i("MySQL", "MySQL open connection");
        }
        @Override
        public String getCode() { return code;}
    }

    //Class B
    private class SQLServer implements DatabaseConnection{
        private String code;
        SQLServer(String codeString){
            code = codeString;
        }
        @Override
        public void openConnection() {
            Log.i("SQL Server", "SQL Server open connection");
        }
        @Override
        public String getCode() { return code;}
    }

    //Class Factory
    private class FactoryClass{
        public DatabaseConnection getDatabaseConnection(String codeString){
            switch (codeString){
                case "MYSQL":
                    return new MySQL("MYSQL");
                case "SQLSERVER":
                    return new SQLServer("SQLSERVER");
                default:
                    return null;
            }
        }
    }

    public void usingFactoryJava(){
        String notSupportedDatabase = "This is not supported database";

        DatabaseConnection mySQLConnection = (new FactoryClass()).getDatabaseConnection("MYSQL");
        if(mySQLConnection!=null){
            mySQLConnection.openConnection();
        }

        DatabaseConnection sqlServerConnection = (new FactoryClass()).getDatabaseConnection("SQLSERVER");
        if(sqlServerConnection!=null){
            sqlServerConnection.openConnection();
        }
    }
}
