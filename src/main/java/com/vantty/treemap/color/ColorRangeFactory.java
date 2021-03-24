package com.vantty.treemap.color;

import java.util.Random;

public class ColorRangeFactory {
    
    public static ColorRange blueForRange(int size) {
        return new ColorRange(size, new RGB(0, 140, 140), new RGB(0, 255, 255));
    }
    
    public static ColorRange redForRange(int size) {
        return new ColorRange(size, new RGB(140, 40, 40), new RGB(255, 40, 40));
    }
    
    public static ColorRange greenForRange(int size) {
        return new ColorRange(size, new RGB(0, 140, 100), new RGB(0, 255, 205));
    }
    
    public static ColorRange customForRange(int size, RGB source, RGB target) {
        return new ColorRange(size, source, target);
    }
    
    public static ColorRange randomForRange(int size) {
        Random random = new Random();
        int r = 20 + random.nextInt(100);
        int g = 20 + random.nextInt(100);
        int b = 20 + random.nextInt(100);
        return new ColorRange(size, new RGB(r, g, b), getTargetColor(random, r, g, b));
        
    }
    
    private static RGB getTargetColor(Random random, int r, int g, int b) {
        return new RGB(r + random.nextInt(255 - r), g + random.nextInt(255 - g), b + random.nextInt(255 - b));
    }
    
}
