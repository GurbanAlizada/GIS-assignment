package com.example.gisassignment1.service;

import com.example.gisassignment1.model.Poi;
import com.example.gisassignment1.repository.PoiRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vividsolutions.jts.geom.Geometry;
import org.springframework.stereotype.Service;
import org.wololo.geojson.Feature;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.gisassignment1.config.GeometryHelper.convertGeoJsonToJtsGeometry;
import static com.example.gisassignment1.config.GeometryHelper.convertJtsGeometryToGeoJson;


@Service
public class PoiService {

    private final PoiRepository poiRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();


    public PoiService(PoiRepository poiRepository) {
        this.poiRepository = poiRepository;
    }


    public void save(Feature feature){
        feature.getGeometry();


        Poi poi = convertFeatureToEntity(feature);


        poiRepository.save(poi);
    }



    private Poi convertFeatureToEntity(Feature feature) {
        Poi entity = new Poi();
//        Map<String, Object> propertiesList = feature.getProperties();
//        Arrays.stream(Poi.class.getDeclaredFields())
//                .filter(i -> !i.isSynthetic())
//                .forEach(i -> {
//                    try {
//                        Field f = Poi.class.getDeclaredField(i.getName());
//                        f.setAccessible(true);
//                        f.set(entity, propertiesList.getOrDefault(i.getName(), null));
//                    } catch (NoSuchFieldException | IllegalAccessException e) {
//                    }
//                });
        entity.setGeometry(convertGeoJsonToJtsGeometry(feature.getGeometry()));
        return entity;
    }


    //    private Feature[] mapEntityListToFeatures(List<Poi> locationEntityList) {
//        return locationEntityList.stream()
//                .map(this::convertEntityToFeature)
//                .toArray(Feature[]::new);
//    }



//    private Feature convertEntityToFeature(Poi entity) {
//        Long id = entity.getId();
//        org.wololo.geojson.Geometry geometry = convertJtsGeometryToGeoJson(entity.getGeometry());
//
//        Map<String, Object> properties = new HashMap<>();
//        Arrays.stream(Poi.class.getDeclaredFields())
//                .filter(i -> !i.isSynthetic())
//                .forEach(field -> {
//                    try {
//                        field.setAccessible(true);
//                        if (field.getType() != Geometry.class && !field.getName().equals("id") && !field.getName().equals("user")) {
//                            properties.put(field.getName(), field.get(entity));
//                        }
//                    } catch (IllegalAccessException e) {
//                    }
//                });
//
//        return new Feature(id, geometry, properties);
//    }
//



}
