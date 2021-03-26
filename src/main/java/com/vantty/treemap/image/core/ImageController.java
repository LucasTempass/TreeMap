package com.vantty.treemap.image.core;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ImageController <R extends FileRequest> {
    
    @RequestMapping(path = "/custom")
    void custom(@RequestBody List<BigDecimal> sequence, @RequestBody R request, HttpServletResponse response);
    
    @RequestMapping(path = "/custom", params = {"colorA", "colorB"})
    void customWithColorParams(@RequestBody List<BigDecimal> sequence, @RequestParam Color colorA, @RequestParam Color colorB, @RequestBody R request,
            HttpServletResponse response);
    
    @RequestMapping(path = "/custom/{color}")
    void customWithColor(@RequestBody List<BigDecimal> sequence, @RequestParam(name = "limit", defaultValue = "10") Integer limit, @RequestParam(name =
            "color", defaultValue = "random") String color, @RequestBody R request, HttpServletResponse response);
    
    @RequestMapping(path = "/{sequenceId}")
    void oeis(@PathVariable(name = "sequenceId") String sequenceId, @RequestParam(name = "limit", defaultValue = "10") Integer limit, @RequestBody R request,
            HttpServletResponse response);
    
    @RequestMapping(path = "/{sequenceId}", params = {"colorA", "colorB"})
    void oeisWithColorParams(@RequestBody String sequenceId, @RequestParam Color colorA, @RequestParam Color colorB, @RequestBody R request,
            HttpServletResponse response);
    
    @RequestMapping(path = "/{sequenceId}/{color}")
    void oeisWithColor(@PathVariable(name = "sequenceId") String sequenceId, @RequestParam(name = "limit", defaultValue = "10") Integer limit,
            @RequestParam(name = "color", defaultValue = "random") String color, @RequestBody R request, HttpServletResponse response);
    
    default void dispatch(FileRequest request, HttpServletResponse response, BufferedImage image) {
        try {
            SupportedImageFormat format = request.format();
            response.setContentType(format.type());
            response.setHeader("Content-Disposition", "inline; filename=\"" + request.title() + format.suffix() + "\"");
            ImageIO.write(image, format.subtype(), response.getOutputStream());
        }
        catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    
}
