package com.corp.k.androidos.designpatterns.structural.proxy

open interface ImageKotlin{
    abstract fun showImage()
}

open class HighResolutionImageKotlin : ImageKotlin {

    constructor(filePath : String){
        loadImageBackground()
    }

    fun loadImageBackground(){
        //load image from disk into memory
        //this is heavy and costly operation
    }

    override fun showImage() {
        //showing image
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

open class ImageProxyKotlin(val filePath : String) : ImageKotlin {

    var proxyImage : ImageKotlin? = null


    override fun showImage() {
        proxyImage = HighResolutionImageKotlin(filePath)
        (proxyImage as HighResolutionImageKotlin).showImage()
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

open class UsingProxyKotlin(){
    open fun Runable(){
        val image1 : ImageKotlin = ImageProxyKotlin("A");
        val image2 : ImageKotlin = ImageProxyKotlin("B");
        val image3 : ImageKotlin = ImageProxyKotlin("C");

        (image1 as ImageProxyKotlin).showImage();
    }
}