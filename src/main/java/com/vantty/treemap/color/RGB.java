package com.vantty.treemap.color;

public class RGB {
    
    private int r;
    private int g;
    private int b;
    
    public RGB(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }
    
    public int r() {
        return r;
    }
    
    public int g() {
        return g;
    }
    
    public int b() {
        return b;
    }
    
    public static RGB fromHexString(String targetColor) {
        if (!targetColor.startsWith("#") || targetColor.length() != 7)
            throw new UnsupportedOperationException("Invalid hex representation");
        int red = Integer.valueOf(targetColor.substring(1, 3), 16);
        int green = Integer.valueOf(targetColor.substring(3, 5), 16);
        int blue = Integer.valueOf(targetColor.substring(5, 7), 16);
        return new RGB(red, green, blue);
        
    }
    
}
