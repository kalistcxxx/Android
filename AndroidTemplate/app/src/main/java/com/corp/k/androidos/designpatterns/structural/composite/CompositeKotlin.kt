package com.corp.k.androidos.designpatterns.structural.composite

open interface ShapeKotlin{
    abstract fun renderShapeToScreen()
    abstract fun explodeImage() : Array<ShapeKotlin>
}

open class A(val name: String){
    constructor(name: String, age: String) : this(name)

}

open class B(val address: String) : A ("a", "b"){
    constructor(name: String, age: String, address: String) : this(address){
    }
}

open class Line(val point1X: Int, val point1Y: Int, val point2X: Int, val point2Y: Int) : ShapeKotlin{
    override fun renderShapeToScreen() {

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun explodeImage(): Array<ShapeKotlin> {
        val shapePart : Array<ShapeKotlin> = arrayOf(this)
        return shapePart
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

open class Rectangle(val rectangleEdges : Array<ShapeKotlin> = arrayOf(Line(-1,-1,1,-1),Line(-1,1,1,1),Line(-1,-1,-1,1),Line(1,-1,1,1))) : ShapeKotlin {
    override fun explodeImage(): Array<ShapeKotlin> {
        return rectangleEdges
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun renderShapeToScreen() {
        for(a in rectangleEdges){
            a.renderShapeToScreen()
        }
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

open class ComplexShape(val shapeList : MutableList<ShapeKotlin>) : ShapeKotlin {
    override fun renderShapeToScreen() {
        for(a in shapeList){
            a.renderShapeToScreen()
        }
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun explodeImage(): Array<ShapeKotlin> {
        return shapeList.toTypedArray()
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

open class UsingCompositeKotlin{
    fun Runable(){

    }
}