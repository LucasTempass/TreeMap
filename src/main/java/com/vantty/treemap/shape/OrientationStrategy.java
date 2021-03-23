package com.vantty.treemap.shape;

public interface OrientationStrategy {

    int getOffset();

    double generateHeight(int position, double previousHeight);

}
