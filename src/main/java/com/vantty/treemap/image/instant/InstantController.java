package com.vantty.treemap.image.instant;

import com.vantty.treemap.color.ColorRange;
import com.vantty.treemap.data.SequenceService;
import com.vantty.treemap.image.core.ImageController;
import com.vantty.treemap.image.ImageService;
import com.vantty.treemap.shape.RectangleFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.List;

import static com.vantty.treemap.color.ColorRangeFactory.*;

@RestController
@RequestMapping(path = "/instant")
public class InstantController implements ImageController<InstantFrameRequest> {
    
    private ImageService imageService;
    private SequenceService sequenceService;
    
    public InstantController(ImageService imageService, SequenceService sequenceService) {
        this.imageService = imageService;
        this.sequenceService = sequenceService;
    }
    
    private BufferedImage buildImage(List<BigDecimal> sequence, InstantFrameRequest request, ColorRange colorRange) {
        var instant = new InstantFrame(request.size(), request.title());
        return imageService.makeInstantImage(new RectangleFactory(sequence, instant.picture()), instant, colorRange);
        
    }
    
    @Override
    public void custom(List<BigDecimal> sequence, InstantFrameRequest request, HttpServletResponse response) {
        var image = buildImage(sequence, request, randomForRange(sequence.size()));
        dispatch(request, response, image);
        
    }
    
    @Override
    public void customWithColorParams(List<BigDecimal> sequence, Color colorA, Color colorB, InstantFrameRequest request, HttpServletResponse response) {
        var image = buildImage(sequence, request, customForRange(sequence.size(), colorA, colorB));
        dispatch(request, response, image);
        
    }
    
    @Override
    public void customWithColor(List<BigDecimal> sequence, Integer limit, String color, InstantFrameRequest request, HttpServletResponse response) {
        var image = buildImage(sequence, request, from(sequence.size(), color));
        dispatch(request, response, image);
        
    }
    
    @Override
    public void oeis(String sequenceId, Integer limit, InstantFrameRequest request, HttpServletResponse response) {
        var sequence = sequenceService.getSequenceById(sequenceId);
        var image = buildImage(sequence, request, randomForRange(sequence.size()));
        dispatch(request, response, image);
        
    }
    
    @Override
    public void oeisWithColorParams(String sequenceId, Color colorA, Color colorB, InstantFrameRequest request, HttpServletResponse response) {
        var sequence = sequenceService.getSequenceById(sequenceId);
        var image = buildImage(sequence, request, customForRange(sequence.size(), colorA, colorB));
        dispatch(request, response, image);
        
    }
    
    @Override
    public void oeisWithColor(String sequenceId, Integer limit, String color, InstantFrameRequest request, HttpServletResponse response) {
        var sequence = sequenceService.getSequenceById(sequenceId);
        var image = buildImage(sequence, request, from(sequence.size(), color));
        dispatch(request, response, image);
        
    }
    
}
