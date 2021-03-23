package com.vantty.treemap;

public class ImageFrame {
    
    private int marginX;
    private int marginY;
    private int width;
    private int height;
    
    public ImageFrame(int marginX, int marginY, int width, int height) {
        this.marginX = marginX;
        this.marginY = marginY;
        this.width = width;
        this.height = height;
    }
    
    public int marginX() {
        return marginX;
    }
    
    public int marginY() {
        return marginY;
    }
    
    public int width() {
        return width;
    }
    
    public int height() {
        return height;
    }
    
    public ImageFrame(int width, int height) {
        this(0, 0, width, height);
    }
    
    public ImageFrame(int size) {
        this(0, 0, size, size);
    }
    
}
