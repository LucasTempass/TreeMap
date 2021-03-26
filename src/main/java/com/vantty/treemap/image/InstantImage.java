package com.vantty.treemap.image;

public class InstantImage {
    
    private final int width;
    private final int marginY;
    private final int marginX;
    private final ImageFrame picture;
    private final int height;
    
    public InstantImage(int width) {
        this.width = width;
        this.marginX = width / 18;
        this.marginY = width / 20;
        int pictureSize = width - marginX * 2;
        this.height = pictureSize + 5 * marginY;
        this.picture = new ImageFrame(marginX, marginY, pictureSize, pictureSize);
    }
    
    public int pictureWidth() {
        return picture.width();
    }
    
    public int pictureHeight() {
        return picture.height();
    }
    
    public int bottomBorderHeight() {
        return marginY * 4;
    }
    
    public int marginY() {
        return marginY;
    }
    
    public int marginX() {
        return marginX;
    }
    
    public ImageFrame picture() {
        return picture;
    }
    
    public int width() {
        return width;
    }
    
    public int height() {
        return height;
    }
    
}
