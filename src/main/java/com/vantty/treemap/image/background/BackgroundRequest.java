package com.vantty.treemap.image.background;

import com.vantty.treemap.image.ImageFrame;
import com.vantty.treemap.image.SupportedImageFormat;

public class BackgroundRequest {
    
    private final String title;
    private final ImageFrame frame;
    private final SupportedImageFormat fileFormat;
    
    public BackgroundRequest(SupportedImageFormat fileFormat, String fileName, ImageFrame frame) {
        this.fileFormat = fileFormat;
        this.title = fileName;
        this.frame = frame;
    }
    
    public BackgroundRequest() {
        this.fileFormat = SupportedImageFormat.PNG;
        this.title = "image";
        this.frame = new ImageFrame(300);
    }
    
    public SupportedImageFormat getFileFormat() {
        return fileFormat;
    }
    
    public String getTitle() {
        return title;
    }
    
    public ImageFrame getFrame() {
        return frame;
    }
    
}


