package com.vantty.treemap;

import com.vantty.treemap.shape.ArtisticRectangleFactory;
import com.vantty.treemap.shape.RectangleFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;

import static com.vantty.treemap.color.ColorRangeFactory.randomForRange;

@RequestMapping(path = "/generate/artistic")
public class ArtisticController {
    
    SequenceService sequenceService;
    
    public ArtisticController(SequenceService sequenceService) {
        this.sequenceService = sequenceService;
    }
    
    @RequestMapping(path = "/{size}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void outputStream(@PathVariable(name = "size") int size, HttpServletResponse response) {
        if (size >= 4080) {throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Max size is 4080");}
        ImageFrame frame = new ImageFrame(size);
        LinkedList<BigDecimal> values = sequenceService.formatValues(SequenceService.getDefaultValues());
        BufferedImage bufferedImage = new DataSetImage(frame, new ArtisticRectangleFactory(values, frame), randomForRange(values.size())).generateBufferedImage();
        try {
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
            response.setHeader("Content-Disposition", "inline; filename=\"treemap.png\"");
            ImageIO.write(bufferedImage, "png", response.getOutputStream());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    @RequestMapping(path = "/")
    public void outputStream(@PathParam(value = "width") Integer width, @PathParam(value = "height") Integer height, HttpServletResponse response) {
        ImageFrame frame = new ImageFrame(width, height);
        LinkedList<BigDecimal> values = sequenceService.formatValues(SequenceService.getDefaultValues());
        BufferedImage bufferedImage = new DataSetImage(frame, new ArtisticRectangleFactory(values, frame), randomForRange(values.size())).generateBufferedImage();
        try {
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
            response.setHeader("Content-Disposition", "inline; filename=\"treemap.png\"");
            ImageIO.write(bufferedImage, "png", response.getOutputStream());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
}