package com.vantty.treemap.shape;

public class HorizontalStrategy implements OrientationStrategy {

    public HorizontalStrategy() { }

    @Override
    public double generateHeight(int position, double previousHeight) {
        return previousHeight;
    }

    @Override
    public int getOffset() {
        return 0;
    }

}
