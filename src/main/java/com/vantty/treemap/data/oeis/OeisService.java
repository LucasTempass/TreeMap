package com.vantty.treemap.data.oeis;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OeisService {
    
    private OiesScraper scraper;
    private OeisRepository repository;
    
    public OeisService(OiesScraper scraper, OeisRepository repository) {
        this.scraper = scraper;
        this.repository = repository;
    }
    
    public List<BigDecimal> getSequenceById(String id) {return null;}
    
}
