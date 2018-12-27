package com.corp.k.androidos.designpatterns.structural.proxy;

public class HighResolutionImage implements Image {

    public HighResolutionImage(String filePath){
        loadImageBackground(filePath);
    }

    private void loadImageBackground(String filePath) {
        //load image from disk into memory
        //this is heavy and costly operation
    }

    @Override
    public void showImage() {
        //Actual image render logic
    }
}
