package com.vantty.treemap.image;

import org.springframework.http.MediaType;

public enum SupportedImageFormat {
    PNG(MediaType.IMAGE_PNG), JPEG(MediaType.IMAGE_JPEG);
    
    private MediaType mediaType;
    
    SupportedImageFormat(MediaType mediaType) {
        this.mediaType = mediaType;
    }
    
    public MediaType getMediaType() {
        return mediaType;
    }
}
