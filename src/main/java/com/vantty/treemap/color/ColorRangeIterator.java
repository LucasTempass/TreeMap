package com.vantty.treemap.color;

import java.awt.*;

public class ColorRangeIterator {

    private int current;
    private ColorRange colorRange;

    public ColorRangeIterator(ColorRange colorRange) {
        this.colorRange = colorRange;
        this.current = 0;
    }

    public Color next() {
        return colorRange.get(current++);
    }


}
