package com.vantty.treemap.color;

import java.awt.*;

public class ColorRange {
    
    private double size;
    private RGB sourceColor;
    private RGB targetColor;
    
    public ColorRange(double size, RGB sourceColor, RGB targetColor) {
        this.size = size;
        this.sourceColor = sourceColor;
        this.targetColor = targetColor;
    }
    
    public Color get(int i) {
        return new Color(getRed(i), getGreen(i), getBlue(i));
    }
    
    private int getRed(int i) {
        return restrictToRange(sourceColor.r() + getIncrementalStep(targetColor.r(), sourceColor.r()) * i);
    }
    
    private int getGreen(int i) {
        return restrictToRange(sourceColor.g() + getIncrementalStep(targetColor.g(), sourceColor.g()) * i);
    }
    
    private int getBlue(int i) {
        return restrictToRange(sourceColor.b() + getIncrementalStep(targetColor.b(), sourceColor.b()) * i);
    }
    
    private double getIncrementalStep(int target, int source) {
        return (target - source) / size;
    }
    
    private int restrictToRange(double value) {
        return (int) Math.max(0, Math.min(255, value));
    }
    
}
