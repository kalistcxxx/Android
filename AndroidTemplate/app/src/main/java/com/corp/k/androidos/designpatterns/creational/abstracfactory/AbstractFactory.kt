package com.corp.k.androidos.designpatterns.creational.abstracfactory

abstract class ComputerKotlin{

    abstract fun getRAM()
    abstract fun getHDD()
    abstract fun getDriver()

    override fun toString() = "RAM = " + getRAM() + ", HDD = " + getHDD() + ", Driver = " + getDriver();

}

class PCKotlin(val Ram: String, val HDD: String, val Driver: String) : ComputerKotlin() {

    override fun getRAM() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getHDD() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDriver() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class ServerKotlin(val Ram: String, val HDD: String, val Driver: String) : ComputerKotlin() {

    override fun getRAM() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getHDD() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDriver() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

interface ComputerFactoryKotlin{
    abstract fun createComputer() : ComputerKotlin
}

class PCFactoryKotlin(val Ram: String, val HDD: String, val Driver: String) : ComputerFactoryKotlin{
    override fun createComputer() : ComputerKotlin {
        return PCKotlin(Ram, HDD, Driver)
    }
}

class ServerFactoryKotlin(val Ram: String, val HDD: String, val Driver: String) : ComputerFactoryKotlin{
    override fun createComputer() : ComputerKotlin {
        return ServerKotlin(Ram, HDD, Driver)
    }
}

class ComputerGenFactoryKotlin{
    fun getComputer(factory : ComputerFactoryKotlin) = factory.createComputer()
}

class UsingAbstractFactory{
    fun Runable(){
        val gen =  ComputerGenFactoryKotlin()
        val pc = gen.getComputer(PCFactoryKotlin("2 GB", "500 GB","2.4 Ghz"))
        val server = gen.getComputer(ServerFactoryKotlin("16 GB", "1T GB","2.8 Ghz"))

        pc.toString()
        server.toString()
    }
}