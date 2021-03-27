package com.vantty.treemap.image.core;

public abstract class FileRequest {
    
    private final String title;
    private final SupportedImageFormat fileFormat;
    
    public FileRequest(String title, SupportedImageFormat fileFormat) {
        this.title = title;
        this.fileFormat = fileFormat;
    }
    
    public FileRequest() {
        this.title = "treemap";
        this.fileFormat = SupportedImageFormat.PNG;
    }
    
    public SupportedImageFormat format() {
        return fileFormat;
    }
    
    public String title() {
        return title;
    }
    
}
