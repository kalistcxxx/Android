package com.corp.k.androidos.designpatterns.structural.proxy;

public class ImageProxy implements Image {
    //private data
    private String filePath;
    //render real subject
    private Image proxyImage;

    public ImageProxy(String stringPath){
        this.filePath = stringPath;
    }

    @Override
    public void showImage() {
        //load image
        proxyImage = new HighResolutionImage(filePath);
        //showing image
        proxyImage.showImage();
    }
}
