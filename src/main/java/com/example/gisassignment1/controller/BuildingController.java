package com.example.gisassignment1.controller;


import com.example.gisassignment1.dto.request.UpdateBuildingRequest;
import com.example.gisassignment1.dto.response.PoiDto;
import com.example.gisassignment1.service.BuildingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wololo.geojson.Feature;
import org.wololo.geojson.FeatureCollection;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/buildings")
public class BuildingController {

    private final BuildingService buildingService;


    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }


    @PostMapping("add-buildings")
    public ResponseEntity<?> save(@RequestBody FeatureCollection featureCollection){
        buildingService.save(featureCollection);
        return ResponseEntity.ok("Success");
    }


    @PostMapping
    public ResponseEntity<Long> addBuilding(@RequestBody Feature feature){
        Long id = buildingService.addBuilding(feature);
        return ResponseEntity.ok(id);
    }


    @PutMapping
    public ResponseEntity<?> updateBuilding(@RequestBody @Valid UpdateBuildingRequest request){
        buildingService.updateBuilding(request);
        return ResponseEntity.ok("Success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        buildingService.delete(id);
        return ResponseEntity.ok("Success");
    }


    @GetMapping
    public ResponseEntity<FeatureCollection> getAll(){
        return ResponseEntity.ok(buildingService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feature> getById(@PathVariable Long id){
        return ResponseEntity.ok(buildingService.getById(id));
    }



    @GetMapping("/getPoi/{id}")
    public ResponseEntity<FeatureCollection> getPoi(@PathVariable Long id){
        return ResponseEntity.ok(buildingService.getPoi(id));
    }





}
