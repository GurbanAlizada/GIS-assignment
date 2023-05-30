package com.example.gisassignment1.controller;


import com.example.gisassignment1.service.LineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wololo.geojson.FeatureCollection;

@RestController
@RequestMapping("/api/v1/lines")
public class LineController {

    private final LineService lineService;


    public LineController(LineService lineService) {
        this.lineService = lineService;
    }


    @PostMapping
    public ResponseEntity<?> save( @RequestBody  FeatureCollection featureCollection){
        lineService.save(featureCollection);
        return ResponseEntity.ok("Success");
    }








}
