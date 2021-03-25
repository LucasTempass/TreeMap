package com.vantty.treemap.color;

import java.awt.*;

public class ColorRange {
    
    private double size;
    private Color sourceColor;
    private Color targetColor;
    
    public ColorRange(double size, Color sourceColor, Color targetColor) {
        this.size = size;
        this.sourceColor = sourceColor;
        this.targetColor = targetColor;
    }
    
    public Color get(int i) {
        return new Color(getRed(i), getGreen(i), getBlue(i));
    }
    
    private int getRed(int i) {
        return restrictToRange(sourceColor.getRed() + getIncrementalStep(targetColor.getRed(), sourceColor.getRed()) * i);
    }
    
    private int getGreen(int i) {
        return restrictToRange(sourceColor.getGreen() + getIncrementalStep(targetColor.getGreen(), sourceColor.getGreen()) * i);
    }
    
    private int getBlue(int i) {
        return restrictToRange(sourceColor.getBlue() + getIncrementalStep(targetColor.getBlue(), sourceColor.getBlue()) * i);
    }
    
    private double getIncrementalStep(int target, int source) {
        return (target - source) / size;
    }
    
    private int restrictToRange(double value) {
        return (int) Math.max(0, Math.min(255, value));
    }
    
}
