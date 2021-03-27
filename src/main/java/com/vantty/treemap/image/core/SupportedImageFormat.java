package com.vantty.treemap.image.core;

import org.springframework.http.MediaType;

public enum SupportedImageFormat {
    PNG(MediaType.IMAGE_PNG), JPEG(MediaType.IMAGE_JPEG);
    
    private MediaType mediaType;
    
    SupportedImageFormat(MediaType mediaType) {
        this.mediaType = mediaType;
    }
    
    public String type() {
        return mediaType.getType();
    }
    
    public String suffix() {
        return "." + mediaType.getSubtype();
    }
    public String subtype() {
        return mediaType.getSubtype();
    }
    
    
    
}
