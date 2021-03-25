package com.vantty.treemap.image;

public class TreeMapRequest {
    
    private final String fileName;
    private final ImageFrame frame;
    private final SupportedImageFormat fileFormat;
    
    public TreeMapRequest(SupportedImageFormat fileFormat, String fileName, ImageFrame frame) {
        this.fileFormat = fileFormat;
        this.fileName = fileName;
        this.frame = frame;
    }
    
    public TreeMapRequest() {
        this.fileFormat = SupportedImageFormat.PNG;
        this.fileName = "image";
        this.frame = new ImageFrame(300);
    }
    
    public SupportedImageFormat getFileFormat() {
        return fileFormat;
    }
    
    public String getFileName() {
        return fileName;
    }
    
    public ImageFrame getFrame() {
        return frame;
    }
    
}


