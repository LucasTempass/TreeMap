package com.vantty.treemap.data;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BigDecimalConverter implements Converter<String, BigDecimal> {
    
    @Override
    public BigDecimal convert(String source) {
        return new BigDecimal(source);
    }
    
}
