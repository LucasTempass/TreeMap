package com.vantty.treemap.image.background;

import com.vantty.treemap.image.core.FileRequest;
import com.vantty.treemap.image.core.ImageFrame;
import com.vantty.treemap.image.core.SupportedImageFormat;

public class BackgroundRequest extends FileRequest {
    
    private final ImageFrame frame;
    
    public BackgroundRequest(SupportedImageFormat fileFormat, String fileName, ImageFrame frame) {
        super(fileName, fileFormat);
        this.frame = frame;
    }
    
    public BackgroundRequest() {
        super();
        this.frame = new ImageFrame(300);
    }
    
    public ImageFrame frame() {
        return frame;
    }
    
}


