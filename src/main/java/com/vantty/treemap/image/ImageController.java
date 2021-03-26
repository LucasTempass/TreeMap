package com.vantty.treemap.image;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

public interface ImageController <R> {
    
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
    
}
