package com.vantty.treemap.image;

import com.vantty.treemap.color.ColorRangeFactory;
import com.vantty.treemap.data.SequenceService;
import com.vantty.treemap.image.instant.InstantFrame;
import com.vantty.treemap.shape.RectangleFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static com.vantty.treemap.data.SequenceService.getDefaultValues;

@RestController
@RequestMapping(path = "/generate")
public class GeneratorController {
    
    private SequenceService sequenceService;
    private ImageService imageService;
    
    public GeneratorController(SequenceService sequenceService, ImageService imageService) {
        this.sequenceService = sequenceService;
        this.imageService = imageService;
    }
    
    @RequestMapping(path = "/custom")
    void sequenceWithLimit(@RequestBody List<BigDecimal> sequence) {}
    
    @RequestMapping(path = "/{color}/custom")
    void customSequenceWithColor(@RequestBody List<BigDecimal> sequence, @RequestParam(name = "color", defaultValue = "random") String color) {
    
    }
    
    @RequestMapping(path = "/{sequenceId}")
    List<BigDecimal> sequenceWithLimit(@PathVariable(name = "sequenceId") String sequence, @RequestParam(name = "limit", defaultValue = "10") Integer limit, @RequestBody TreeMapRequest request) {
        return null;
    }
    
    @RequestMapping(path = "/")
    void reu(HttpServletResponse response) {
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
    
    @RequestMapping(path = "/{color}/{sequenceId}")
    void sequenceWithColor(@PathVariable(name = "sequenceId") String sequence, @RequestParam(name = "limit", defaultValue = "10") Integer limit, @RequestParam(name = "color", defaultValue = "random") String color) {
        
    }
    
 
}
