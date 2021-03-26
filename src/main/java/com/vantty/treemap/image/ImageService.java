package com.vantty.treemap.image;

import com.vantty.treemap.color.ColorRange;
import com.vantty.treemap.color.ColorRangeIterator;
import com.vantty.treemap.shape.RectangleFactory;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;
import static java.awt.Font.BOLD;

@Service
public class ImageService {
    
    public static final Color DARKER_WHITE = new Color(240, 240, 240);
    public static final String FONT_NAME = "Oswald";
    
    public BufferedImage generatePolaroidImage(RectangleFactory factory, InstantImage frame, ColorRange colorRange, String title) {
        BufferedImage image = new BufferedImage(frame.width(), frame.height(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        drawBackground(graphics, frame);
        drawTitle(graphics, frame, title);
        drawImage(graphics, factory, new ColorRangeIterator(colorRange));
        return image;
        
    }
    
    private void drawBackground(Graphics2D graphics, InstantImage frame) {
        graphics.setColor(WHITE);
        graphics.setPaint(new GradientPaint(0, 0, DARKER_WHITE, frame.width(), frame.height(), WHITE));
        graphics.fillRect(0, 0, frame.width(), frame.height());
        
    }
    
    private int getFontOffset(int size, double proportion) {
        return (int) (size * proportion);
    }
    
    private void drawTitle(Graphics2D graphics, InstantImage frame, String title) {
        graphics.setColor(BLACK);
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        // graphics.setFont(new Font("Open Sans Condensed", Font.BOLD, (int) (frame.marginY() * 1.5)));
        graphics.setFont(new Font(FONT_NAME, BOLD, (int) (frame.marginY() * 1.5)));
        int width = graphics.getFontMetrics().stringWidth(title);
        graphics.drawString(title, titleX(frame, width), titleY(frame, graphics.getFont().getSize()));
        
    }
    
    private int titleY(InstantImage frame, int titleHeight) {
        return frame.pictureHeight() + frame.marginY() + (frame.bottomBorderHeight() / 2) + getFontOffset(titleHeight, 0.45);
    }
    
    private int titleX(InstantImage frame, int titleWidth) {
        return frame.width() - frame.marginX() - titleWidth - (frame.marginX() / 2);
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
