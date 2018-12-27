package com.corp.k.androidos.designpatterns.creational.prototype

open class PrototypeKotlin(val listAtts: MutableList<String>) : Cloneable{

    open fun loadData(){
        listAtts.add("A")
        listAtts.add("B")
        listAtts.add("C")
        listAtts.add("D")
        listAtts.add("E")
    }

    fun getAttsList() = listAtts

    override fun clone(): PrototypeKotlin {
        super.clone()
        val listClone : MutableList<String> = mutableListOf<String>();
        for (item in listAtts){
            listClone.add(item);
        }
        return PrototypeKotlin(listClone)
    }
}

class UsingPrototype{
    fun runable(){
        val pro1 = PrototypeKotlin(mutableListOf<String>())
        pro1.loadData()

        val pro2 = pro1
        pro2.getAttsList().removeAt(2)
    }
}