package com.vantty.treemap.image;

import com.vantty.treemap.data.SequenceService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@RequestMapping(path = "/generate/artistic")
public class ArtisticController {
    
    private SequenceService sequenceService;
    private ImageService imageService;
    
    public ArtisticController(SequenceService sequenceService, ImageService imageService) {
        this.sequenceService = sequenceService;
        this.imageService = imageService;
    }
    
    @RequestMapping(path = "/custom")
    void sequenceWithLimit(@RequestBody List<BigDecimal> sequence) {}
    
    @RequestMapping(path = "/{color}/custom")
    void customSequenceWithColor(@RequestBody List<BigDecimal> sequence, @RequestParam(name = "color", defaultValue = "random") String color) {}
    
    @RequestMapping(path = "/{sequenceId}")
    void sequenceWithLimit(@PathVariable(name = "sequenceId") String sequence, @RequestParam(name = "limit", defaultValue = "10") Integer limit) {}
    
    @RequestMapping(path = "/{color}/{sequenceId}")
    void sequenceWithColor(@PathVariable(name = "sequenceId") String sequence, @RequestParam(name = "limit", defaultValue = "10") Integer limit,
            @RequestParam(name = "color", defaultValue = "random") String color) {
        
    }
    
}
