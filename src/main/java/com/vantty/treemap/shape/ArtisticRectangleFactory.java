package com.vantty.treemap.shape;

import com.vantty.treemap.ImageFrame;

import java.util.LinkedList;

public abstract class ArtisticRectangleFactory extends RectangleFactory {

    public ArtisticRectangleFactory(LinkedList<Double> values, ImageFrame frame) {
        super(values, frame);
    }

    @Override
    protected double generatePreviousHeight(int position, OrientationStrategy orientation) {
        int index = position + orientation.getOffset();
        //||| Wrong proportions, generates an aesthetic feeling |||
        double previousHeight = imageFrame.height() * getRatioForElements(-orientation.getOffset(), 0);
        for (int ignored = 0; position / 4 > ignored; ignored++, index -= 4)
            previousHeight *= (values.get(index - 2) / values.get(index - 4));
        return previousHeight;

    }

}
