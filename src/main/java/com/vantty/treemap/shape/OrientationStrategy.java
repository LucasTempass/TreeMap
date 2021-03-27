package com.vantty.treemap.shape;

import java.math.BigDecimal;
import java.util.List;

public interface OrientationStrategy {
    
    int getOffset();
    
    double generateHeight(int position, double previousHeight, List<BigDecimal> values);
    
}
