package com.example.gisassignment1.controller;


import com.example.gisassignment1.service.PoiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wololo.geojson.FeatureCollection;

@RestController
@RequestMapping("/v1/api/poi")
public class PoiController {

    private final PoiService poiService;


    public PoiController(PoiService poiService) {
        this.poiService = poiService;
    }


    @PostMapping
    public ResponseEntity<?> postLocation(@RequestBody FeatureCollection featureCollection) {
         poiService.save( featureCollection);
         return ResponseEntity.ok("Success");
    }



}
