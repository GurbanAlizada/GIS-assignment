package com.example.gisassignment1.controller;


import com.example.gisassignment1.service.PoiService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.wololo.geojson.Feature;

@RestController
@RequestMapping("/v1/api/poi")
public class PoiController {

    private final PoiService poiService;


    public PoiController(PoiService poiService) {
        this.poiService = poiService;
    }



    @RequestMapping(value = "/location", method = RequestMethod.POST)
    public ResponseEntity postLocation(@RequestBody Feature feature) {

         poiService.save( feature);

        UriComponentsBuilder ucBuilder = UriComponentsBuilder.newInstance();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/location/{id}").buildAndExpand(1).toUri());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }





}
