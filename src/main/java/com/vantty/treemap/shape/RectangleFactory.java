package com.vantty.treemap.shape;

import com.vantty.treemap.ImageFrame;

import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

public class RectangleFactory {

    protected final ImageFrame imageFrame;
    protected LinkedList<java.lang.Double> values;

    public RectangleFactory(LinkedList<java.lang.Double> values, ImageFrame frame) {
        this.imageFrame = frame;
        this.values = values;
    }

    public Rectangle2D.Double buildFor(int position, OrientationStrategy orientation) {
        return new Rectangle2D.Double(generateX(position, orientation), generateY(position, orientation), generateWidth(position, orientation), orientation.generateHeight(position, generatePreviousHeight(position, orientation)));
    }

    protected double generateWidth(int position, OrientationStrategy orientation) {
        int index = position + orientation.getOffset();
        double width = imageFrame.width();
        for (int ignore = 0; position / 4 > ignore; ignore++, index -= 4)
            width *= getRatioForElements(index, index - 2);
        return width;

    }

    protected double generatePreviousHeight(int position, OrientationStrategy orientation) {
        int index = position + orientation.getOffset();
        int height = imageFrame.height();
        for (int ignore = 0; position / 4 > ignore; ignore++, index -= 4)
            height *= getRatioForElements(index - 2, index - 4);
        return height;

    }

    protected double generateX(int position, OrientationStrategy orientation) {
        return imageFrame.marginX() + imageFrame.width() - generateWidth(position, orientation);
    }

    protected double generateY(int position, OrientationStrategy orientation) {
        if (position == 0)
            return imageFrame.marginY();
        return imageFrame.marginY() + generateBaseElementHeight() - orientation.generateHeight(position, generatePreviousHeight(position, orientation));

    }

    protected double generateBaseElementHeight() {
        return imageFrame.height() * getRatioForElements(2, 0);
    }

    protected double getRatioForElements(int firstIndex, int secondIndex) {
        return values.get(firstIndex) / values.get(secondIndex);
    }

}
