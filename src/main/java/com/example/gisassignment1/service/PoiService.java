package com.example.gisassignment1.service;

import com.example.gisassignment1.model.Building;
import com.example.gisassignment1.util.GeometryHelper;
import com.example.gisassignment1.model.Poi;
import com.example.gisassignment1.repository.PoiRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wololo.geojson.Feature;
import org.wololo.geojson.FeatureCollection;

import java.util.*;


@Service
public class PoiService {

    private final PoiRepository poiRepository;


    public PoiService(PoiRepository poiRepository) {
        this.poiRepository = poiRepository;
    }


    @Transactional
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


    protected Feature convertEntityToFeature(Poi entity) {
        Long id = entity.getId();
        org.wololo.geojson.Geometry geometry = GeometryHelper.convertJtsGeometryToGeoJson(entity.getGeometry());

        Map<String, Object> properties = new HashMap<>();
        Arrays.stream(Poi.class.getDeclaredFields())
                .filter(i -> !i.isSynthetic())
                .forEach(field -> {
                    try {
                        field.setAccessible(true);
                        if (field.getType() != com.vividsolutions.jts.geom.Geometry.class && !field.getName().equals("id") && !field.getName().equals("user")) {
                            properties.put(field.getName(), field.get(entity));
                        }
                    } catch (IllegalAccessException e) {
                    }
                });

        return new Feature(id, geometry, properties);
    }



    protected List<Poi> findAll(){
      return   poiRepository.findAll();
    }


}
