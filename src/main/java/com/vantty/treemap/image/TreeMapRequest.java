package com.vantty.treemap.image;

public class TreeMapRequest {
    
    private final String title;
    private final ImageFrame frame;
    private final SupportedImageFormat fileFormat;
    
    public TreeMapRequest(SupportedImageFormat fileFormat, String fileName, ImageFrame frame) {
        this.fileFormat = fileFormat;
        this.title = fileName;
        this.frame = frame;
    }
    
    public TreeMapRequest() {
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


