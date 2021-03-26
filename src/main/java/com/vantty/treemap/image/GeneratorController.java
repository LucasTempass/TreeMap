package com.vantty.treemap.image;

import com.vantty.treemap.color.ColorRangeFactory;
import com.vantty.treemap.data.SequenceService;
import com.vantty.treemap.image.instant.InstantFrame;
import com.vantty.treemap.shape.RectangleFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static com.vantty.treemap.color.ColorRangeFactory.*;
import static com.vantty.treemap.data.SequenceService.getDefaultValues;

@RestController
@RequestMapping(path = "/generate")
public class GeneratorController implements ImageController<TreeMapRequest> {
    
    private SequenceService sequenceService;
    private ImageService imageService;
    
    public GeneratorController(SequenceService sequenceService, ImageService imageService) {
        this.sequenceService = sequenceService;
        this.imageService = imageService;
    }
    
    @RequestMapping(path = "/")
    void demo(HttpServletResponse response) {
        var values = sequenceService.formatValues(getDefaultValues());
        String title = "A000010";
        InstantFrame frame = new InstantFrame(1080, title);
        var image = imageService.makeInstantImage(new RectangleFactory(values, frame.picture()), frame, ColorRangeFactory.randomForRange(values.size()));
        try {
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
            response.setHeader("Content-Disposition", "inline; filename=\"" + title + ".png\"");
            ImageIO.write(image, "png", response.getOutputStream());
        }
        catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        
    }
    
    @Override
    public void custom(List<BigDecimal> sequence, TreeMapRequest request, HttpServletResponse response) {
        var image = imageService.makeFramelessImage(new RectangleFactory(sequence, request.getFrame()), request.getFrame(), randomForRange(sequence.size()));
    }
    
    @Override
    public void customWithColorParams(List<BigDecimal> sequence, Color colorA, Color colorB, TreeMapRequest request, HttpServletResponse response) {
        var image = imageService.makeFramelessImage(new RectangleFactory(sequence, request.getFrame()), request.getFrame(), customForRange(sequence.size(), colorA, colorB));
    }
    
    @Override
    public void customWithColor(List<BigDecimal> sequence, Integer limit, String color, TreeMapRequest request, HttpServletResponse response) {
        var image = imageService.makeFramelessImage(new RectangleFactory(sequence, request.getFrame()), request.getFrame(), ColorRangeFactory.from(sequence.size(), color));
    }
    
    @Override
    public void oeis(String sequenceId, Integer limit, TreeMapRequest request, HttpServletResponse response) {
        var sequence = sequenceService.getSequenceById(sequenceId);
        var image = imageService.makeFramelessImage(new RectangleFactory(sequence, request.getFrame()), request.getFrame(), randomForRange(sequence.size()));
        
    }
    
    @Override
    public void oeisWithColorParams(String sequenceId, Color colorA, Color colorB, TreeMapRequest request, HttpServletResponse response) {
        var sequence = sequenceService.getSequenceById(sequenceId);
        var image = imageService.makeFramelessImage(new RectangleFactory(sequence, request.getFrame()), request.getFrame(), customForRange(sequence.size(), colorA, colorB));
    }
    
    @Override
    public void oeisWithColor(String sequenceId, Integer limit, String color, TreeMapRequest request, HttpServletResponse response) {
        var sequence = sequenceService.getSequenceById(sequenceId);
        var image = imageService.makeFramelessImage(new RectangleFactory(sequence, request.getFrame()), request.getFrame(), from(sequence.size(), color));
    
    }
    
}
