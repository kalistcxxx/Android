package com.corp.k.androidos.designpatterns.structural.adapter

open class VoltKotlin(val volt: Int){

}

open class SocketKotlin(){
    fun getVolts() = VoltKotlin(120)
}

interface SocketAdapterKotlin{
    abstract fun get120Volt(): VoltKotlin
    abstract fun get12Volt(): VoltKotlin
    abstract fun get3Volt(): VoltKotlin
}

class SocketObjectAdapterImplKotlin(val socket : SocketKotlin) : SocketAdapterKotlin{
    override fun get120Volt(): VoltKotlin {
        return socket.getVolts()
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get12Volt(): VoltKotlin {
        val v = socket.getVolts()
        return convertVolt(v , 10)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get3Volt(): VoltKotlin {
        val v = socket.getVolts()
        return convertVolt(v , 10)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun convertVolt(v: VoltKotlin, i: Int) = VoltKotlin(v.volt / i)
}

class SocketAdapterImplKotlin : SocketKotlin(), SocketAdapterKotlin{
    override fun get120Volt(): VoltKotlin {
        return getVolts()
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get12Volt(): VoltKotlin {
        val v = getVolts()
        return convertVolt(v , 10)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get3Volt(): VoltKotlin {
        val v = getVolts()
        return convertVolt(v , 10)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun convertVolt(v: VoltKotlin, i: Int) = VoltKotlin(v.volt / i)
}

class UsingAdapterKotlin{
    fun Runable(){
        val socketImp = SocketAdapterImplKotlin()
        val socketObjectImp = SocketObjectAdapterImplKotlin(socket = SocketKotlin())

        socketImp.get120Volt()
        socketObjectImp.get120Volt()
    }
}