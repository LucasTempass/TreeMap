package com.vantty.treemap.image;

import com.vantty.treemap.color.ColorRange;
import com.vantty.treemap.color.ColorRangeIterator;
import com.vantty.treemap.image.instant.InstantFrame;
import com.vantty.treemap.shape.RectangleFactory;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;

@Service
public class ImageService {
    
    public BufferedImage makeFramelessImage(RectangleFactory factory, ImageFrame frame, ColorRange colorRange) {
        BufferedImage image = new BufferedImage(frame.width(), frame.height(), BufferedImage.TYPE_INT_RGB);
        drawImage(image.createGraphics(), factory, new ColorRangeIterator(colorRange));
        return image;
        
    }
    
    public BufferedImage makeInstantImage(RectangleFactory factory, InstantFrame frame, ColorRange colorRange) {
        drawImage(frame.graphics(), factory, new ColorRangeIterator(colorRange));
        return frame.asBufferedImage();
        
    }
    
    private void drawImage(Graphics2D graphics2D, RectangleFactory factory, ColorRangeIterator colorRangeIterator) {
        graphics2D.setColor(colorRangeIterator.next());
        for (int index = 0; index < factory.size(); index += 2) {
            graphics2D.setColor(colorRangeIterator.next());
            if (index % 4 == 0)
                graphics2D.fill(factory.buildHorizontal(index));
            else graphics2D.fill(factory.buildVertical(index));
        }
        
    }
    
}
