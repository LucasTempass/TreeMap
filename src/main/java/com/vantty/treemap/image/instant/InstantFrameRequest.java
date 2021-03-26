package com.vantty.treemap.image.instant;

import com.vantty.treemap.image.SupportedImageFormat;

public class InstantFrameRequest {
    
    private final int size;
    private final String title;
    private final SupportedImageFormat format;
    
    public InstantFrameRequest(int size, String title, SupportedImageFormat format) {
        this.size = size;
        this.title = title;
        this.format = format;
    }
    
    public SupportedImageFormat format() {
        return format;
    }
    
    public int size() {
        return size;
    }
    
    public String title() {
        return title;
    }
    
}
