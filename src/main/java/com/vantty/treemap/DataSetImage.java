package com.vantty.treemap;

import com.vantty.treemap.color.ColorRangeIterator;
import com.vantty.treemap.shape.ArtisticRectangleFactory;
import com.vantty.treemap.shape.HorizontalStrategy;
import com.vantty.treemap.shape.VerticalStrategy;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.LinkedList;

public class DataSetImage {
    
    private final ImageFrame frame;
    private final LinkedList<BigDecimal> values;
    private final ColorRangeIterator colorRangeIterator;
    
    public DataSetImage(ImageFrame frame, LinkedList<BigDecimal> values, ColorRangeIterator iterator) {
        this.frame = frame;
        this.values = values;
        this.colorRangeIterator = iterator;
    }
    
    public BufferedImage generateBufferedImage() {
        BufferedImage bufferedImage = new BufferedImage(frame.width(), frame.height(), BufferedImage.TYPE_INT_RGB);
        paint(bufferedImage.createGraphics());
        return bufferedImage;
        
    }
    
    private void paint(Graphics2D graphics2D) {
        graphics2D.setColor(colorRangeIterator.next());
        var factory = new ArtisticRectangleFactory(values, frame);
        var horizontal = new HorizontalStrategy();
        var vertical = new VerticalStrategy(values);
        for (int index = 0; index < values.size(); index += 2) {
            graphics2D.setColor(colorRangeIterator.next());
            if (index % 4 == 0)
                graphics2D.fill(factory.buildFor(index, horizontal));
            else
                graphics2D.fill(factory.buildFor(index, vertical));
        }
        
    }
    
}
