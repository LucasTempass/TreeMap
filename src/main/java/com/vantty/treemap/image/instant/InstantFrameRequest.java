package com.vantty.treemap.image.instant;

public class InstantFrameRequest {
    
    private final int size;
    private final String title;
    
    public InstantFrameRequest(int size, String title) {
        this.size = size;
        this.title = title;
    }
    
    public int size() {
        return size;
    }
    
    public String title() {
        return title;
    }
    
}
