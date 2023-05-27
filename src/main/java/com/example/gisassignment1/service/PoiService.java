package com.example.gisassignment1.service;

import com.example.gisassignment1.config.GeometryHelper;
import com.example.gisassignment1.model.Poi;
import com.example.gisassignment1.repository.PoiRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.wololo.geojson.Feature;
import org.wololo.geojson.FeatureCollection;

import java.util.ArrayList;
import java.util.List;


@Service
public class PoiService {

    private final PoiRepository poiRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();


    public PoiService(PoiRepository poiRepository) {
        this.poiRepository = poiRepository;
    }


    public void save(FeatureCollection featureCollection){
        Feature[] features = featureCollection.getFeatures();
        List<Poi> pois = new ArrayList<>();

        for (Feature element : features){
            pois.add(convertFeatureToEntity(element));
        }
        poiRepository.saveAll(pois);
    }


    private Poi convertFeatureToEntity(Feature feature) {
        Poi entity = new Poi();
        entity.setGeometry(GeometryHelper.convertGeoJsonToJtsGeometry(feature.getGeometry()));
        return entity;
    }



}
