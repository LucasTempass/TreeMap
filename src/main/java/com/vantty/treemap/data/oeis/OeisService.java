package com.vantty.treemap.data.oeis;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OeisService {
    
    private OiesScrapingService scraper;
    private OeisRepository repository;
    
    public OeisService(OiesScrapingService scraper, OeisRepository repository) {
        this.scraper = scraper;
        this.repository = repository;
    }
    
    public List<BigDecimal> getSequenceById(String id) throws SequenceNotFoundException {
        return repository.findById(id).orElse(scraper.retrieve(id));
    }
    
}
