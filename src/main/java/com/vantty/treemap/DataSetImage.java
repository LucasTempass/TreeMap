package com.vantty.treemap;

import com.vantty.treemap.color.ColorRange;
import com.vantty.treemap.color.ColorRangeIterator;
import com.vantty.treemap.shape.RectangleFactory;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DataSetImage {
    
    private final ImageFrame frame;
    private final ColorRangeIterator colorRangeIterator;
    private final RectangleFactory factory;
    
    public DataSetImage(ImageFrame frame, RectangleFactory factory, ColorRange colorRange) {
        this.frame = frame;
        this.factory = factory;
        this.colorRangeIterator = new ColorRangeIterator(colorRange);
    }
    
    public BufferedImage generateBufferedImage() {
        BufferedImage bufferedImage = new BufferedImage(frame.width(), frame.height(), BufferedImage.TYPE_INT_RGB);
        paint(bufferedImage.createGraphics());
        return bufferedImage;
        
    }
    
    private void paint(Graphics2D graphics2D) {
        graphics2D.setColor(colorRangeIterator.next());
        for (int index = 0; index < factory.size(); index += 2) {
            graphics2D.setColor(colorRangeIterator.next());
            if (index % 4 == 0)
                graphics2D.fill(factory.buildHorizontal(index));
            else graphics2D.fill(factory.buildVertical(index));
        }
        
    }
    
}
