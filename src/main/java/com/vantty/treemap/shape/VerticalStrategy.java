package com.vantty.treemap.shape;

import java.util.LinkedList;

public class VerticalStrategy implements OrientationStrategy {

    private LinkedList<java.lang.Double> values;

    public VerticalStrategy(LinkedList<Double> values) {
        this.values = values;
    }

    @Override
    public double generateHeight(int position, double previousHeight) {
        return (values.get(position) / values.get(position - 2)) * previousHeight;
    }

    @Override
    public int getOffset() {
        return -2;
    }

}
