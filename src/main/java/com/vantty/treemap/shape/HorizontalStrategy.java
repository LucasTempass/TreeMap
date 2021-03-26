package com.vantty.treemap.shape;

import java.math.BigDecimal;
import java.util.List;

public class HorizontalStrategy implements OrientationStrategy {
    
    public HorizontalStrategy() { }
    
    @Override
    public double generateHeight(int position, double previousHeight, List<BigDecimal> values) {
        return previousHeight;
    }
    
    @Override
    public int getOffset() {
        return 0;
    }
    
}
