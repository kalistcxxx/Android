package com.corp.k.androidos.designpatterns.creational.factory

/**
 * Created by hoangtuan on 7/11/17.
 */

/**
 *  Example about Factory
 */
interface DatabaseConnection{
    val code: String
    fun openConnect();
}

class MySQL(override val code: String = "MYSQL") : DatabaseConnection {
    override fun openConnect() {
        print("MySQL open connection");
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class SQLServer(override val code: String = "SQLSERVER") : DatabaseConnection {
    override fun openConnect() {
        print("SQL Server open connection");
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

enum class Database{
    MYSQL, SQLSERVER
}

class FactoryClass{
    fun getDatabaseConnection(code: Database): DatabaseConnection?{
        when(code){
            Database.MYSQL -> return MySQL()
            Database.SQLSERVER -> return SQLServer()
            else                -> return null
        }
    }
}

class UsingFactory{
    fun runable(){
        val notSupportDatabase = "This is not supported database"
        val mySQLConnection = FactoryClass().getDatabaseConnection(Database.MYSQL)?.code ?: notSupportDatabase
        val sqlServerConnection = FactoryClass().getDatabaseConnection(Database.SQLSERVER)?.code ?: notSupportDatabase

        val MySqlServer = FactoryClass().getDatabaseConnection(Database.MYSQL)
        MySqlServer?.openConnect();
    }
}