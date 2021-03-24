package com.vantty.treemap.shape;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.LinkedList;

public class VerticalStrategy implements OrientationStrategy {
    
    private LinkedList<BigDecimal> values;
    
    public VerticalStrategy(LinkedList<BigDecimal> values) {
        this.values = values;
    }
    
    @Override
    public double generateHeight(int position, double previousHeight) {
        return getRatio(position) * previousHeight;
    }
    
    private double getRatio(int position) {
        return values.get(position).divide(values.get(position - 2), MathContext.DECIMAL64).doubleValue();
    }
    
    @Override
    public int getOffset() {
        return -2;
    }
    
}
