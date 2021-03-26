package com.vantty.treemap.image.instant;

import com.vantty.treemap.color.ColorRangeFactory;
import com.vantty.treemap.data.SequenceService;
import com.vantty.treemap.image.ImageService;
import com.vantty.treemap.shape.RectangleFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

import static com.vantty.treemap.color.ColorRangeFactory.customForRange;
import static com.vantty.treemap.color.ColorRangeFactory.randomForRange;

@RestController
@RequestMapping(path = "/instant")
public class InstantController {
    
    private ImageService imageService;
    private SequenceService sequenceService;
    
    public InstantController(ImageService imageService, SequenceService sequenceService) {
        this.imageService = imageService;
        this.sequenceService = sequenceService;
    }
    
    @RequestMapping(path = "/custom")
    void sequenceWithLimit(@RequestBody List<BigDecimal> sequence, @RequestBody InstantFrameRequest request, HttpServletResponse response) {
        var instant = buildFromRequest(request);
        var image = imageService.makeInstantImage(new RectangleFactory(sequence, instant.picture()), instant, randomForRange(sequence.size()));
        
    }
    
    @RequestMapping(path = "/custom", params = {"colorA", "colorB"})
    void customSequenceWithColor(@RequestBody List<BigDecimal> sequence, @RequestParam Color colorA, @RequestParam Color colorB,
            @RequestBody InstantFrameRequest request) {
        var instant = buildFromRequest(request);
        var image = imageService.makeInstantImage(new RectangleFactory(sequence, instant.picture()), instant, customForRange(sequence.size(), colorA, colorB));
        
    }
    
    @RequestMapping(path = "/{sequenceId}/{color}")
    void customSequenceWithColor(@RequestBody List<BigDecimal> sequence, @RequestParam(name = "limit", defaultValue = "10") Integer limit,
            @RequestParam(name = "color", defaultValue = "random") String color, @RequestBody InstantFrameRequest request) {
        var instant = buildFromRequest(request);
        var image = imageService.makeInstantImage(new RectangleFactory(sequence, instant.picture()), instant, ColorRangeFactory.from(color));
        
    }
    
    @RequestMapping(path = "/{sequenceId}")
    void sequenceWithLimit(@PathVariable(name = "sequenceId") String sequenceId, @RequestParam(name = "limit", defaultValue = "10") Integer limit,
            @RequestBody InstantFrameRequest request) {
        List<BigDecimal> sequence = sequenceService.getSequenceById(sequenceId);
        var instant = buildFromRequest(request);
        var image = imageService.makeInstantImage(new RectangleFactory(sequence, instant.picture()), instant, randomForRange(sequence.size()));
        
    }
    
    @RequestMapping(path = "/{sequenceId}/{color}")
    void sequenceWithColor(@PathVariable(name = "sequenceId") String sequence, @RequestParam(name = "limit", defaultValue = "10") Integer limit,
            @RequestParam(name = "color", defaultValue = "random") String color, @RequestBody InstantFrameRequest request) {
        
    }
    
    @RequestMapping(path = "/{sequenceId}/{color}", params = {"colorA", "colorB"})
    void sequenceWithColor(@RequestBody String sequenceId, @RequestParam Color colorA, @RequestParam Color colorB,
            @RequestBody InstantFrameRequest request) {
        var instant = buildFromRequest(request);
        var sequence = sequenceService.getSequenceById(sequenceId);
        var image = imageService.makeInstantImage(new RectangleFactory(sequence, instant.picture()), instant, customForRange(sequence.size(), colorA, colorB));
        
    }
    
    private InstantFrame buildFromRequest(@RequestBody InstantFrameRequest request) {
        return new InstantFrame(request.size(), request.title());
    }
    
}
