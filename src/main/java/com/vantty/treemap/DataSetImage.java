package com.vantty.treemap;

import com.vantty.treemap.color.ColorRange;
import com.vantty.treemap.color.ColorRangeIterator;
import com.vantty.treemap.shape.RectangleFactory;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.RenderingHints.KEY_TEXT_LCD_CONTRAST;

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
        BufferedImage bufferedImage = new BufferedImage(frame.width() + frame.marginX() * 2, frame.height() + (frame.marginY() * 5),
                BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.setPaint(new GradientPaint(0, 0, new Color(240, 240, 240), frame.width() + frame.marginX() * 2, frame.height() + (frame.marginY() * 5),
                new Color(255, 255, 255)));
        graphics.fillRect(0, 0, frame.width() + frame.marginX() * 2, frame.height() + (frame.marginY() * 5));
        graphics.setColor(Color.BLACK);
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
       // graphics.setFont(new Font("Open Sans Condensed", Font.BOLD, (int) (frame.marginY() * 1.5)));
        graphics.setFont(new Font("Oswald", Font.BOLD, (int) (frame.marginY() * 1.5)));
        int width = graphics.getFontMetrics().stringWidth("A01110");
        int bottomBorderHeight = frame.marginY() * 4;
        graphics.drawString("A01110", frame.width() - width + ((int) (frame.marginX() * 0.2)),
                frame.height() + frame.marginY() + (bottomBorderHeight / 2) + ((int) (graphics.getFont().getSize() * 0.45)));
        paint(graphics);
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
        graphics2D.setBackground(Color.WHITE);
        
    }
    
}
