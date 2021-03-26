package com.vantty.treemap.image;

import com.vantty.treemap.color.ColorRangeFactory;
import com.vantty.treemap.data.SequenceService;
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
    void customSequenceWithColor(@RequestBody List<BigDecimal> sequence, @RequestParam(name = "color", defaultValue = "random") String color) {}
    
    @RequestMapping(path = "/{sequenceId}")
    List<BigDecimal> sequenceWithLimit(@PathVariable(name = "sequenceId") String sequence, @RequestParam(name = "limit", defaultValue = "10") Integer limit) {
        return null;
    }
    
    @RequestMapping(path = "/")
    void reu(HttpServletResponse response) {
        var values = sequenceService.formatValues(getDefaultValues());
        String title = "A000010";
        InstantImage frame = new InstantImage(1080, title);
        var image = imageService.generatePolaroidImage(new RectangleFactory(values, frame.picture()), frame, ColorRangeFactory.randomForRange(values.size()));
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
    void sequenceWithColor(@PathVariable(name = "sequenceId") String sequence, @RequestParam(name = "limit", defaultValue = "10") Integer limit,
            @RequestParam(name = "color", defaultValue = "random") String color) {
        
    }
    
    //    @RequestMapping(path = "/{size}", produces = MediaType.IMAGE_JPEG_VALUE)
    //    public void outputStream(@PathVariable(name = "size") int size, HttpServletResponse response) {
    //        if (size >= 4080)
    //            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Max size is 4080");
    //        ImageFrame frame = new ImageFrame(size);
    //        LinkedList<BigDecimal> values = sequenceService.formatValues(SequenceService.getDefaultValues());
    //        BufferedImage bufferedImage = imageService.generateImage(new RectangleFactory(values, frame), frame, randomForRange(values.size()));
    //        try {
    //            response.setContentType(MediaType.IMAGE_PNG_VALUE);
    //            response.setHeader("Content-Disposition", "inline; filename=\"treemap.png\"");
    //            ImageIO.write(bufferedImage, "png", response.getOutputStream());
    //        }
    //        catch (IOException e) {
    //            e.printStackTrace();
    //        }
    //
    //    }
    //
    //    //    response.setHeader("Content-Disposition", "attachment; filename=\"treemap.png\"");
    //
    //    @RequestMapping(path = "/")
    //    public void outputStream(@RequestBody TreeMapRequest treeMapRequest, @Nullable @RequestBody FileRequest fileRequest, HttpServletResponse response) {
    //        LinkedList<BigDecimal> values = sequenceService.formatValues(SequenceService.getDefaultValues());
    //        BufferedImage bufferedImage = imageService.generateImage(new RectangleFactory(values, treeMapRequest.frame()), treeMapRequest.frame(),
    //                randomForRange(values.size()));
    //        try {
    //            response.setContentType(MediaType.IMAGE_PNG_VALUE);
    //            response.setHeader("Content-Disposition", "inline; filename=\"treemap.png\"");
    //            ImageIO.write(bufferedImage, "png", response.getOutputStream());
    //        }
    //        catch (IOException e) {
    //            e.printStackTrace();
    //        }
    //
    //    }
    //
}
