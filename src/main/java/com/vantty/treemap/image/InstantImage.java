package com.vantty.treemap.image;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;
import static java.awt.Font.BOLD;

public class InstantImage {
    
    private final int width;
    private final int height;
    private final int marginX;
    private final int marginY;
    private final String title;
    private Graphics2D graphics;
    private final ImageFrame picture;
    private final BufferedImage image;
    public static final String FONT_NAME = "Oswald";
    public static final Color DARKER_WHITE = new Color(240, 240, 240);
    
    public InstantImage(int width, String title) {
        this.width = width;
        this.marginX = width / 18;
        this.marginY = width / 20;
        int pictureSize = width - marginX * 2;
        this.height = pictureSize + 5 * marginY;
        this.picture = new ImageFrame(marginX, marginY, pictureSize, pictureSize);
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.title = title;
        
    }
    
    public BufferedImage asBufferedImage() {
        return image;
    }
    
    public Graphics2D graphics() {
        if (graphics == null)
            this.graphics = image.createGraphics();
        drawBackground(graphics);
        drawTitle(graphics, title);
        return graphics;
        
    }
    
    private void drawBackground(Graphics2D graphics) {
        graphics.setColor(WHITE);
        graphics.setPaint(new GradientPaint(0, 0, DARKER_WHITE, width, height, WHITE));
        graphics.fillRect(0, 0, width, height);
        
    }
    
    private void drawTitle(Graphics2D graphics, String title) {
        graphics.setColor(BLACK);
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        // graphics.setFont(new Font("Open Sans Condensed", Font.BOLD, (int) (marginY() * 1.5)));
        graphics.setFont(new Font(FONT_NAME, BOLD, (int) (marginY * 1.5)));
        int width = graphics.getFontMetrics().stringWidth(title);
        graphics.drawString(title, titleX(width), titleY(graphics.getFont().getSize()));
        
    }
    
    private int getFontOffset(int size) {
        return (int) (size * 0.45);
    }
    
    private int titleY(int titleHeight) {
        return height - (bottomBorderHeight() / 2) + getFontOffset(titleHeight);
    }
    
    private int titleX(int titleWidth) {
        return width - marginX - titleWidth - (marginX / 2);
    }
    
    public int bottomBorderHeight() {
        return marginY * 4;
    }
    
    public ImageFrame picture() {
        return picture;
    }
    
}
