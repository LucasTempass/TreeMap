package com.vantty.treemap.image;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class ColorConverter implements Converter<String, Color> {
    
    @Override
    public Color convert(String source) {
        return Color.decode(source);
    }
    
}
