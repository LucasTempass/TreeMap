package com.vantty.treemap.data.oeis;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class OeisRepository {
    
    Optional<List<BigDecimal>> findById(String id) {
        return Optional.empty();
    }
    
}
