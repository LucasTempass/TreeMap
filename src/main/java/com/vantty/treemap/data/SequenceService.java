package com.vantty.treemap.data;

import com.vantty.treemap.data.oeis.OeisService;
import com.vantty.treemap.data.oeis.SequenceNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SequenceService {
    
    private final OeisService oeisService;
    
    public SequenceService(OeisService oeisService) {
        this.oeisService = oeisService;
    }
    
    public List<BigDecimal> getSequenceById(String id) throws SequenceNotFoundException {
        return formatValues(oeisService.getSequenceById(id));
    }
    
    public LinkedList<BigDecimal> formatValues(List<BigDecimal> doubles) {
        doubles.sort(Collections.reverseOrder());
        LinkedList<BigDecimal> values = new LinkedList<>();
        values.add(doubles.get(doubles.size() - 1));
        for (int e = doubles.size() - 2; e >= 0; e--) {
            if (values.getFirst().equals(doubles.get(e)))
                continue;
            values.addFirst(doubles.get(e));
            values.addFirst(doubles.get(e).add(values.get(1)));
        }
        return values;
        
    }
    
    public static List<BigDecimal> getDefaultValues() {
        //12.0, 11.0, 10.0, 9.0, 8.0, 7.0, 6.0, 5.0, 4.0, 3.0, 2.0, 1.0
        //1, 1, 2, 2, 4, 2, 6, 4, 6, 4, 10, 4, 12, 6, 8, 8, 16, 6, 18, 8, 12, 10, 22, 8, 20, 12, 18, 12, 28
        //0, 1, 36, 1225, 41616, 1413721, 48024900, 1631432881
        return List.of(1, 1, 2, 2, 4, 2, 6, 4, 6, 4, 10, 4, 12, 6, 8, 8, 16, 6, 18, 8, 12, 10, 22, 8, 20, 12, 18, 12, 28).stream().map(BigDecimal::new).collect(Collectors.toList());
    }
    
}