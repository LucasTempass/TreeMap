package com.vantty.treemap;

import com.vantty.treemap.color.ColorRangeFactory;
import com.vantty.treemap.color.ColorRangeIterator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/generate")
public class GeneratorController {
    
    @RequestMapping(path = "/{size}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void outputStream(@PathVariable(name = "size") int size, HttpServletResponse response) {
        if (size >= 4080)
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Max size is 1080;");
        try {
            ImageIO.write(new DataSetImage(new ImageFrame(size), formatValues(getDefaultValues()),
                    new ColorRangeIterator(ColorRangeFactory.randomForRange(12))).generateBufferedImage(), "png", response.getOutputStream());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    private LinkedList<BigDecimal> formatValues(List<BigDecimal> doubles) {
        doubles.sort(Collections.reverseOrder());
        LinkedList<BigDecimal> values = new LinkedList<>();
        values.add(doubles.get(doubles.size() - 1));
        for (int e = doubles.size() - 2; e >= 0; e--) {
            if (values.getFirst().equals(doubles.get(e)))
                continue;
            values.addFirst(doubles.get(e));
            values.addFirst(doubles.get(e).add(values.get(1)));
        }
        return values;
        
    }
    
    public static List<BigDecimal> getDefaultValues() {
        //12.0, 11.0, 10.0, 9.0, 8.0, 7.0, 6.0, 5.0, 4.0, 3.0, 2.0, 1.0
        return List.of(1, 1, 2, 2, 4, 2, 6, 4, 6, 4, 10, 4, 12, 6, 8, 8, 16, 6, 18, 8, 12, 10, 22, 8, 20, 12, 18, 12, 28).stream().map(BigDecimal::new).collect(Collectors.toList());
    }
    
}
