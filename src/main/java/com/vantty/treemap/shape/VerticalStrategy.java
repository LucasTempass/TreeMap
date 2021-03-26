package com.vantty.treemap.shape;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

public class VerticalStrategy implements OrientationStrategy {
    
    public VerticalStrategy() { }
    
    @Override
    public double generateHeight(int position, double previousHeight, List<BigDecimal> values) {
        return getRatio(position, values) * previousHeight;
    }
    
    private double getRatio(int position, List<BigDecimal> values) {
        return values.get(position).divide(values.get(position - 2), MathContext.DECIMAL64).doubleValue();
    }
    
    @Override
    public int getOffset() {
        return -2;
    }
    
}
