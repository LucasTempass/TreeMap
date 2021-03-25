package com.vantty.treemap.shape;

import com.vantty.treemap.image.ImageFrame;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.LinkedList;

public class ArtisticRectangleFactory extends RectangleFactory {
    
    public ArtisticRectangleFactory(LinkedList<BigDecimal> values, ImageFrame frame) {
        super(values, frame);
    }
    
    @Override
    protected double generatePreviousHeight(int position, OrientationStrategy orientation) {
        int index = position + orientation.getOffset();
        //||| Wrong proportions, generates an aesthetic feeling |||
        double previousHeight = imageFrame.height() * getRatioForElements(-orientation.getOffset(), 0);
        for (int ignored = 0; position / 4 > ignored; ignored++, index -= 4)
            previousHeight *= (values.get(index - 2).divide(values.get(index - 4), MathContext.DECIMAL64)).doubleValue();
        return previousHeight;
        
    }
    
}
