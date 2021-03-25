package com.vantty.treemap.image;

public class PolaroidImageFrame extends ImageFrame {
    
    //TODO make it target size
    
    public PolaroidImageFrame(int size) {
        super(size / 20, size / 20, size, size);
    }
    
    public int frameWidth() {
        return super.width() + marginX() * 2;
    }
    
    public int frameHeight() {
        return super.height() + marginY() * 5;
    }
    
    public int getBottomBorderHeight() {
        return marginY() * 4;
    }
    
}
