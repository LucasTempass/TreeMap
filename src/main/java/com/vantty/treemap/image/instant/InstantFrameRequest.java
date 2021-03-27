package com.vantty.treemap.image.instant;

import com.vantty.treemap.image.core.FileRequest;
import com.vantty.treemap.image.core.SupportedImageFormat;

public class InstantFrameRequest extends FileRequest {
    
    private final int size;
    
    public InstantFrameRequest(int size, String title, SupportedImageFormat format) {
        super(title, format);
        this.size = size;
    }
    
    public InstantFrameRequest() {
        super();
        this.size = 1080;
    }
    
    public int size() {
        return size;
    }
    
}
