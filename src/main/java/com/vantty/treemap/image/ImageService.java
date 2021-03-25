package com.vantty.treemap.image;

import com.vantty.treemap.color.ColorRange;
import com.vantty.treemap.color.ColorRangeIterator;
import com.vantty.treemap.image.ImageFrame;
import com.vantty.treemap.image.TreeMapRequest;
import com.vantty.treemap.shape.RectangleFactory;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;

@Service
public class ImageService {
    
    public BufferedImage generateImage(TreeMapRequest request, RectangleFactory factory) {
        var image = new BufferedImage(request.width(), request.height(), BufferedImage.TYPE_INT_RGB);
        paint(image.createGraphics(), factory, new ColorRangeIterator(new ColorRange(request.sequenceSize(), request.sourceColor(), request.targetColor())));
        return image;
        
    }
    
    public BufferedImage generateImage(RectangleFactory factory, ImageFrame frame, ColorRange colorRange) {
        var image = new BufferedImage(frame.width(), frame.height(), BufferedImage.TYPE_INT_RGB);
        paint(image.createGraphics(), factory, new ColorRangeIterator(colorRange));
        return image;
        
    }
    
    private void paint(Graphics2D graphics2D, RectangleFactory factory, ColorRangeIterator colorRangeIterator) {
        graphics2D.setColor(colorRangeIterator.next());
        for (int index = 0; index < factory.size(); index += 2) {
            graphics2D.setColor(colorRangeIterator.next());
            if (index % 4 == 0)
                graphics2D.fill(factory.buildHorizontal(index));
            else graphics2D.fill(factory.buildVertical(index));
        }
        
    }
    
}
