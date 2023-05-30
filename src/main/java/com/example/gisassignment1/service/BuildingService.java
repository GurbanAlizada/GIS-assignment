package com.example.gisassignment1.service;


import com.example.gisassignment1.util.GeometryHelper;
import com.example.gisassignment1.dto.request.UpdateBuildingRequest;
import com.example.gisassignment1.dto.response.PoiDto;
import com.example.gisassignment1.exception.BuildingNotFoundException;
import com.example.gisassignment1.model.Building;
import com.example.gisassignment1.model.Poi;
import com.example.gisassignment1.repository.BuildingRepository;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wololo.geojson.Feature;
import org.wololo.geojson.FeatureCollection;

import java.util.*;

@Service
public class BuildingService {


    private final BuildingRepository buildingRepository;
    private final PoiService poiService;

    public BuildingService(BuildingRepository buildingRepository, PoiService poiService) {
        this.buildingRepository = buildingRepository;
        this.poiService = poiService;
    }


    @Transactional
    public void save(FeatureCollection featureCollection){

        Feature[] features = featureCollection.getFeatures();

        for (Feature element  :features){
            if (buildingRepository.checkIsExistsTheSameBuildings(GeometryHelper.convertGeoJsonToJtsGeometry(element.getGeometry())) == 0){
                buildingRepository.save(convertFeatureToEntity(element));
            }
        }
    }


    @Transactional
    public Long addBuilding(Feature feature) {
        Building building = convertFeatureToEntity(feature);
        Building fromDb = buildingRepository.save(building);
        return fromDb.getId();
    }


    @Transactional
    public void updateBuilding(UpdateBuildingRequest request) {
        Building newBuilding = convertFeatureToEntity(request.feature());
        Building buildingFromDb = getBuildingById(request.id());

        buildingFromDb.setGeometry(newBuilding.getGeometry());
        buildingFromDb.setIndex(newBuilding.getIndex());
        buildingRepository.save(buildingFromDb);
    }



    @Transactional
    public void delete(Long id) {
        Building building = getBuildingById(id);
        buildingRepository.delete(building);
    }


    public FeatureCollection getAll() {
        List<Building> buildings = buildingRepository.findAll();
        Feature[] features = mapEntityListToFeatures(buildings);
        return new FeatureCollection(features);
    }


    public Feature getById(Long id) {
        Building building = getBuildingById(id);
        Feature feature = convertEntityToFeature(building);
        return feature;
    }


    public FeatureCollection getPoi(Long id) {
        Building building = getBuildingById(id);
        Geometry geometry = building.getGeometry();
        List<Poi> pois = poiService.findAll();

        List<Feature> features = new ArrayList<>();


        for (Poi element : pois){
            if (geometry.contains(element.getGeometry())){
                Feature feature = poiService.convertEntityToFeature(element);
                System.out.println(feature);
                features.add(feature);
            }
        }

        Feature[] featureArr = new Feature[features.size()];
        for (int i = 0 ; i < features.size() ; i++){
            featureArr[i] = features.get(i);
        }

        return new FeatureCollection(featureArr);

    }





    private Building convertFeatureToEntity(Feature feature) {
        Building entity = new Building();
        Object index = feature.getId();
        if (index != null){
            entity.setIndex(Long.valueOf(index.toString()));
        }
        entity.setGeometry(GeometryHelper.convertGeoJsonToJtsGeometry(feature.getGeometry()));
        return entity;
    }




    private Feature[] mapEntityListToFeatures(List<Building> buildings) {
        return buildings
                .stream()
                .map(this::convertEntityToFeature)
                .toArray(Feature[]::new);
    }



    private Feature convertEntityToFeature(Building entity) {
        Long id = entity.getId();
        org.wololo.geojson.Geometry geometry = GeometryHelper.convertJtsGeometryToGeoJson(entity.getGeometry());

        Map<String, Object> properties = new HashMap<>();
        Arrays.stream(Building.class.getDeclaredFields())
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


    private Building getBuildingById(Long id){
        Building building = buildingRepository.findById(id)
                .orElseThrow(() -> new BuildingNotFoundException("building could not found , id : " +  id));

        return building;
    }



}
